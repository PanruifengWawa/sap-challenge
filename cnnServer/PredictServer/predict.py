from django.http import JsonResponse
from train_model import cnnPredict

time = 0


def predict_post(request):
    para = ""
    response = {"result": "", "status": 0}
    if request.GET:
        para = request.GET["sentence"]
        res = cnnPredict.predict_model(para)
        response = {"result": res, "status": 0}
        return JsonResponse(response)
    else:
        response["result"] = "method not allowed"
        response["status"] = 1
        return JsonResponse(response)