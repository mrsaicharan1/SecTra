from django.shortcuts import render, get_object_or_404, redirect
from django.http import HttpResponse, HttpResponseRedirect
from django.core.files.storage import FileSystemStorage
from django.urls import reverse
from .models import *
from django.db.models import Q
import subprocess as s
import os


def index(request):
	s.call('python3 ../../models-master/research/object_detection/object_detection_tutorial.py', shell=True)
	return HttpResponse("OK")