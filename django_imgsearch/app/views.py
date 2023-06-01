# -*- coding: utf-8 -*-
import json

from rest_framework.decorators import action
from rest_framework.parsers import MultiPartParser
from rest_framework.views import APIView

from .extract_cnn_vgg16_keras import VGGNet
from django.views.generic import  View
from django_redis import get_redis_connection
from django.http import  HttpResponse,JsonResponse
import requests
import numpy as np

from drf_yasg2.utils import swagger_auto_schema
from drf_yasg2 import openapi

feat_key='img_2'

# Create your views here.
class ImageSearch(APIView):
    """
    测试redis
    """
    @action(methods=['get'], detail=False)
    def get(self,request):
        conn = get_redis_connection("default")
        print(conn.smembers('dengzhen'),'\n')
        return JsonResponse({'data':json.dumps([i.decode() for i in conn.smembers('dengzhen') ])})

class HGetByName(APIView):
    """
    根据名称获取特征值，
    """
    name = openapi.Parameter(name='name', in_=openapi.IN_QUERY, description="图片名称,xxxx.jpg",
                                    type=openapi.TYPE_STRING)

    @swagger_auto_schema(method='get', manual_parameters=[name])
    @action(methods=['get'], detail=False)
    def get(self,request):
        data = request.query_params
        q = data.get('name')
        if not  q:
            return JsonResponse({'code':200,'message':'请输入name'})
        else:
            conn = get_redis_connection("default")
            list = conn.hget(feat_key,q)
            if not list:
                return JsonResponse({'code':200,'message':'数据库中不存在该图片'})
            else:
                return JsonResponse({q:list.decode()})

class HDel(APIView):
    """
    根据图片名称删除，特征值
    """

    request_body = openapi.Schema(type=openapi.TYPE_OBJECT,
                                  required=['name', ], properties=
                                  {'name': openapi.Schema(type=openapi.TYPE_STRING, description='xxxxx.jpg'),
                                   }
                                  )

    @swagger_auto_schema(method='post', request_body=request_body)
    @action(methods=['post'], detail=False, )
    def post(self,request):
        postbody = request.body
        json_param = json.loads(postbody.decode())
        conn = get_redis_connection("default")
        len = conn.hdel(feat_key,json_param.get('name',0))
        return JsonResponse({'data':len})

# 上传文件转化为特征值
class ImageUpload(APIView):
    parser_classes = (MultiPartParser,)  # 解析form表单，注意 必须添加这一行
    """
    文件上传,搜索
    """

    def __init__(self):
        self.model = VGGNet()
        self.conn = get_redis_connection("default")

    file  = openapi.Parameter(name='file', in_=openapi.IN_FORM, description="文件上传",
                                    type=openapi.TYPE_FILE)
    @swagger_auto_schema(method='post', manual_parameters=[file], )
    @action(methods=['post'], detail=False,
            parser_classes=(MultiPartParser, ))
    def post(self,request):
        pic_obj = request.FILES.get('file')
        name=pic_obj.name

        queryVec = self.model.vgg_extract_feat2(pic_obj.read())


        data =  self.conn.hgetall(feat_key)
        imgNames = [k.decode() for k, v in data.items()]
        feats = [v.decode() for k, v in data.items()]
        feats = [json.loads(i) for i in feats]
        feats = np.array(feats)


        scores = np.dot(queryVec, feats.T)
        rank_ID = np.argsort(scores)[::-1]
        rank_score = scores[rank_ID]
        print(rank_score)

        maxres = 10  # 检索出三张相似度最高的图片
        imlist = []
        for i, index in enumerate(rank_ID[0:maxres]):
            imlist.append({'name':"http://localhost:8008/img/upload/file/"+imgNames[index],'value':rank_score[i]})
            print("image names: " + str(imgNames[index]) + " scores: %f" % rank_score[i])

        return JsonResponse({'data':imlist})

class GetImgFeat(APIView):
    """
       获取图像特征值，所有
    """
    @action(methods=['get'], detail=False)
    def get(self,request):
        conn = get_redis_connection("default")
        content = conn.hgetall(feat_key)
        len = conn.hlen(feat_key)
        content = {k.decode('utf-8'): v.decode('utf-8') for k, v in content.items()}
        return JsonResponse({'length':len,'data':content})

class SetFeatByUrl(APIView):
    """
       提取网络图片特征值,并存入redis
    """

    request_body = openapi.Schema(type=openapi.TYPE_OBJECT,
                                  required=['url', ], properties=
                                  {'url': openapi.Schema(type=openapi.TYPE_STRING, description='http://.......jpg/png网络图片'),
                                   }
                                  )

    @swagger_auto_schema(method='post', request_body=request_body, )
    @action(methods=['post'], detail=False, )
    def post(self,request):
        header = {
            'user-agen': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36'
        }
        postbody = request.body
        json_param = json.loads(postbody.decode())
        conn = get_redis_connection("default")
        model = VGGNet()
        try:
            name = json_param.get('url',0).split('/')[-1]
            response = requests.get(json_param.get('url',0), headers=header, timeout=5)
            queryVec = model.vgg_extract_feat2(response.content)
            dic = {}
            dic[name] = queryVec.tolist()
            len = conn.hset(feat_key, name, json.dumps(queryVec.tolist()))
            dic2={}
            dic2['code']=len
            return JsonResponse(dic2)
        except:
            dic3={}
            dic3['code']=404
            return JsonResponse(dic3)
