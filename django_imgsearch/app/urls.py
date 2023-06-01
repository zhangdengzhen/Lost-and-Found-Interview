# -*- coding: utf-8 -*-
from django.urls import path
from .views import ImageSearch,ImageUpload,GetImgFeat,SetFeatByUrl,HDel,HGetByName
urlpatterns=[
    path('img',ImageSearch.as_view()),
    path('upload', ImageUpload.as_view()),
    path('getfeat', GetImgFeat.as_view()),
    path('setfeatbyurl', SetFeatByUrl.as_view()),
    path('delebyname',HDel.as_view()),
    path('getbyname',HGetByName.as_view())
]