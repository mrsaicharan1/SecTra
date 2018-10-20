from google_images_download import google_images_download

response = google_images_download.googleimagesdownload()

<<<<<<< HEAD
arguments = {"keywords":"Cigarette,power-bank,sharp blade","limit":500,"print_urls":True,'px':'172.31.1.4:8080'}   #creating list of arguments
=======
arguments = {"keywords":"Cigarette,blades","limit":100,"print_urls":True,'px':'172.31.1.4:8080'}   #creating list of arguments
>>>>>>> 50a186de63dc673a11076e4a0174142128a1025c

paths = response.download(arguments)
print(paths)
