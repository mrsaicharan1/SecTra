from google_images_download import google_images_download

response = google_images_download.googleimagesdownload()

arguments = {"keywords":"Cigarette,power-bank,sharp blade","limit":500,"print_urls":True,'px':'172.31.1.4:8080'}   #creating list of arguments

paths = response.download(arguments)
print(paths)
