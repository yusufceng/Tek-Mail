from firebase import firebase

firebase = firebase.FirebaseApplication(
            "your/firebase/app/link", None)

while True:
	myGetResult=firebase.get('/User',None)
	print(myGetResult)
