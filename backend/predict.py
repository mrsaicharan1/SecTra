import os
from imageai.Prediction.Custom import CustomImagePrediction

execution_path = os.getcwd()
prediction = CustomImagePrediction()
prediction.setModelTypeAsResNet()
prediction.setModelPath(os.path.join(execution_path,'model_ex-077_acc-0.781250.h5'))
prediction.setJsonPath(os.path.join(execution_path, "model_class.json"))
prediction.loadModel(num_objects=1)

predictions, probabilities = prediction.predictImage(os.path.join(execution_path, "4.jpg"), result_count=5)
for eachPrediction, eachProbability in zip(predictions, probabilities):
    print(eachPrediction + " : " + eachProbability)
