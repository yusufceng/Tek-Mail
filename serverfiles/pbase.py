import pyrebase

config = {
    "apiKey": "AIzaSyChItBcOZBkYiIpsg-vgW1nuI0BCSuxaSk",
    "authDomain": "https://tek-mail.firebaseapp.com/",
    "databaseURL": "https://tek-mail.firebaseio.com",
    "storageBucket": "tek-mail.appspot.com"
}

firebase = pyrebase.initialize_app(config)

db = firebase.database()

def stream_handler(message):
    print(message["event"]) # put
    print(message["path"]) # /-K7yGTTEp7O549EzTYtI
    print(message["data"]) # {'title': 'Pyrebase', "body": "etc..."}


my_stream = db.child("tek-mail").stream(stream_handler)
