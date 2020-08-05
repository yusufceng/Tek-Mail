curl -X POST "http://YOUR_DOMAIN/api/v1/delete/mailbox" -H "accept: application/json" -H "X-API-Key: YOUR_API_KEY" -H "Content-Type: application/json" -d "[ \"$1@YOUR_DOMAIN\" ]" --insecure
