from django.http import JsonResponse
from train_model import cnnPredict
from keras.models import load_model

model = cnnPredict.MyModel()


def predict_post(request):
    para = ""
    response = {"result": "", "status": 0}
    #model = cnnPredict.MyModel()
    global model
    if request.GET:
        para = request.GET["sentence"]
        res = model.predict_model(para)
        response = {"result": res, "status": 0}
        return JsonResponse(response)
    else:
        response["result"] = "method not allowed"
        response["status"] = 1
        return JsonResponse(response)

