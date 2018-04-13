from keras.models import load_model
import sys
import tensorflow.contrib.keras as kr


class MyModel:
    _instance = None

    def __init__(self):
        # 读取词汇表并编号
        self.words, self.word_to_id = self.read_vocab("./train_model/cnews.vocab.txt")
        # 加载模型
        self.myModel = load_model("./train_model/model.h5")
        test1 = ["测试数据"]
        test2 = self.process_content(test1, self.word_to_id)
        self.myModel.predict(test2)

    def __new__(cls, *args, **kwargs):
        if not cls._instance:
                cls._instance = super(MyModel, cls).__new__(cls, *args, **kwargs)
        return cls._instance

    def open_file(self, filename, mode='r'):
        """
        常用文件操作
        mode: 'r' or 'w' for read or write
        """
        return open(filename, mode, encoding='utf-8', errors='ignore')


    def read_vocab(self, vocab_dir):
        """读取词汇表"""
        words = self.open_file(vocab_dir).read().strip().split('\n')
        word_to_id = dict(zip(words, range(len(words))))
        return words, word_to_id


    def process_content(self,contents, word_to_id, max_length=100):
        """将文件转换为id表示"""
        data_id = []
        for i in range(len(contents)):
            data_id.append([word_to_id[x] for x in contents[i] if x in word_to_id])
        # 使用keras提供的pad_sequences来将文本pad为固定长度
        x_content = kr.preprocessing.sequence.pad_sequences(data_id, max_length)
        return x_content


    def predict_model(self,sentence):
        data = [sentence]
        datap = self.process_content(data, self.word_to_id)
        predicted = self.myModel.predict(datap)
        kr.backend.clear_session()
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
    model = MyModel()
    res = model.predict_model(sys.argv[1])
