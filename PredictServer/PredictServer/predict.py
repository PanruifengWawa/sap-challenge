from django.http import JsonResponse
from sap-challenge.train_model.cnnPredict import *


def predict_post(request):
    para = ""
    response = {"result":"","status": 0}
    if request.GET:
        para = request.GET["word"]
        print(para)
        return JsonResponse(response)
    else:
        response["result"] = "method not allowed"
        response["status"] = 1
        return JsonResponse(response)