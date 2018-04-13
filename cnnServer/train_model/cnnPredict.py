from keras.models import load_model
import tensorflow.contrib.keras as kr
import numpy as np
# test part


def open_file(filename, mode='r'):
    """
    常用文件操作
    mode: 'r' or 'w' for read or write
    """
    return open(filename, mode, encoding='utf-8', errors='ignore')


def read_vocab(vocab_dir):
    """读取词汇表"""
    words = open_file(vocab_dir).read().strip().split('\n')
    word_to_id = dict(zip(words, range(len(words))))
    return words, word_to_id


def process_content(contents, word_to_id, max_length=100):
    """将文件转换为id表示"""
    data_id = []
    for i in range(len(contents)):
        data_id.append([word_to_id[x] for x in contents[i] if x in word_to_id])
    # 使用keras提供的pad_sequences来将文本pad为固定长度
    x_content = kr.preprocessing.sequence.pad_sequences(data_id, max_length)
    return x_content


def predict_model(sentence):
    # 载入模型
    myModel = load_model("./model.h5")
    # 读取词汇表并编号
    words, word_to_id = read_vocab("./cnews.vocab.txt")
    # 测试数据
    test = [sentence]
    testp = process_content(test, word_to_id)
    predicted = myModel.predict(testp)
    result = predicted.tolist()
    label = result[0].index(max(result[0]))
    if label == 0:
        res = "AT"
    elif label == 1:
        res = "CI"
    elif label == 2:
        res = "PI"
    elif label == 3:
        res = "WE"
    else:
        res = "NN"
    print(res)
    return res


if __name__ == "__main__":
    predict_model("是谁在唱歌")
    predict_model("公司在哪里")
    predict_model("是谁在唱歌")
    predict_model("公司在哪里")
    predict_model("是谁在唱歌")
    predict_model("公司在哪里")
    predict_model("是谁在唱歌")
    predict_model("公司在哪里")