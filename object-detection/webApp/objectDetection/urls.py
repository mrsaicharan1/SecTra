from django.conf.urls import url, include
from django.contrib import admin
from .views import FileView
from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^upload/$', FileView.as_view(), name='file-upload'),
]