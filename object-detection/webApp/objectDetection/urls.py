from django.conf.urls import url, include
from django.contrib import admin
from . import views
from .views import FileView

app_name = 'app'

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^upload/$', FileView.as_view(), name='file-upload'),
]