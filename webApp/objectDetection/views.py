from django.shortcuts import render, get_object_or_404, redirect
from django.http import HttpResponse, HttpResponseRedirect
from django.core.files.storage import FileSystemStorage
from django.urls import reverse
from .models import *
from django.db.models import Q
import subprocess as s
import os

from rest_framework.views import APIView
from rest_framework.parsers import MultiPartParser, FormParser
from rest_framework.response import Response
from rest_framework import status	

from .serializers import FileSerializer


def index(request):
	#s.call('python3 ../../models-master/research/object_detection/object_detection_tutorial.py', shell=True)
	return HttpResponse("OK")
	#return render(request, 'index.html', {})


class FileView(APIView):
    parser_classes = (MultiPartParser, FormParser)
    def post(self, request, *args, **kwargs):
    	file_serializer = FileSerializer(data=request.data)
    	if file_serializer.is_valid():
    		file_serializer.save()
    		return Response(file_serializer.data, status=status.HTTP_201_CREATED)
    	else:
    		return Response(file_serializer.errors, status=status.HTTP_400_BAD_REQUEST)