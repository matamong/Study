# Sklearn n_folds error

## n_folds 쓸 때 에러
n_folds를 적용하려고 할 때, unexpected keyword argument 'n_folds' 가 뜬다.

## 해결

Sklearn 이 업데이트 되면서 n_folds 가 n_splits 로 바뀌었다.
n_splits 을 쓰자.

* * *
[Stack Overflow](https://stackoverflow.com/questions/63429051/typeerror-init-got-an-unexpected-keyword-argument-n-folds)