# katalon-taskapi-angelicanatalie

Task API Testing Automation using Katalon Studio - GITS 2024
QAGT002GIT001 - Angelica Natalie

Pengujian API Automation dengan method GET, POST, dan DELETE dilakukan pada http://jsonplaceholder.typicode.com/, menguji 2 endpoint, yaitu albums dan photos.
Setiap pengujian dilakukan secara data driven testing sehingga untuk masing-masing endpoint dilakukan konversi dari file .json ke .csv lalu di-import menjadi test data.
Juga dibuat test suite yang berisi seluruh test cases terkait endpoint tersebut, berikut penjelasannya:

1. /album
      - Web Service Requests: GET albums, POST album, DELETE album
      - Test Data: TD-albums
      - Test Case: TC-getAlbums, TC-postAlbum, TC-deleteAlbum
      - Test Suite: TS-albums
2. /photos
      - Web Service Requests: GET photos, POST photo, DELETE photo
      - Test Data: TD-photos
      - Test Case: TC-getPhotos, TC-postPhoto, TC-deletePhoto
      - Test Suite: TS-photos
