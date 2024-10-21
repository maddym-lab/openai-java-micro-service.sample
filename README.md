Sample micro-service end points for enabling prompt to integrate with openai using ImageModel and Rest call.
Sample payload as below:
{
    "model": "gpt-4o-mini",
    "prompt": "Get Sydney Opera House picture",
    "height": "1024",
    "width" : 1024,
    "numOfResponses": 1
}
Note: I know that it's not a good practice to have request payload for GET end points. To apply best API best parctices, a potential solution can be to prebuild search configuration w/ a unique ID (say in a database) per use case and then pass the search unique ID to fetch the payload (similar to above) and then use it to submit the request. 
