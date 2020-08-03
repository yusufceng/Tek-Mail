from firebase import firebase

firebase = firebase.FirebaseApplication(
            "https://tek-mail.firebaseio.com/", None)

while True:
	myGetResult=firebase.get('/User',None)
	print(myGetResult)
