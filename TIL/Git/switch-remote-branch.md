# Remote 브랜치 가져오기

- 등록되어있는 remote를 갱신한다.
```git
$ git remote update
```
<br>

- 잘 갱신되어있는지 remote의 브랜치를 확인한다.
```git
$ git branch -r 
  origin/develop
  origin/main
```
<br>

- 현재 나의 브랜치 상황을 한 번 확인한다.
```git
$ git branch -a
  *main
  remotes/origin/main
```

<br>

- 가져올 브랜치를 선택해서 checkout한다.
```git
$ git checkout -t origin/develop
```

<br>

- 해당 remote브랜치가 로컬브랜치로 checkout되었는지 확인한다. 
```git
$ git branch -a
* develop
  main
  remotes/origin/develop
  remotes/origin/main
```