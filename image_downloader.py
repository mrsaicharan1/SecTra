from google_images_download import google_images_download

response = google_images_download.googleimagesdownload()

arguments = {"keywords":"Cigarette,blades","limit":100,"print_urls":True,'px':'172.31.1.4:8080'}   #creating list of arguments

paths = response.download(arguments)
