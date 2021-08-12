# Channels 로 채팅 구현


Channels란 ? 장고의 ASGI 기반 라이브러리.

- 기존의 `WSGI`에서 비동기방식을 더한 **`ASGI`** 기반으로 만들어진 라이브러리 
- Websocket이나 radio같이 통신시간이 길고 막힘이 없어야 하는 프로토콜을 다룰 수 있게된다.
    - **`websocket`** : 하나의 프로토콜. http 프로토콜이 있듯이 wesocket 프로토콜이 있는 것이다. HTTP와는 달리 **접속을 계속 유지할 수 있는 프로토콜**이다.
- 기존 장고의 Native Asynchronous View Support를 감싸기 때문에 장고의 **Auth나 Session등과 같은 기능과 같이 사용** 할 수 있다.

<br><br>

## Consumer

- Channel의 Consumer는 장고의 View와 같은 역할을 한다.
- Django의 View는 Request가 들어오면 그것에 대해 다룬 뒤, Response를 내보낸다.
- Channel의 Consumer는 **일단 연결이 되어있는 상태라면 다수의 이벤트를 비동기로 다룰 수 있다.** (View처럼 Reqeust만 받으며 쓸 수는 있다. 그렇게 쓰여질 라이브러리가 아니라서 그렇지!)
- 예를 들어 어느 앱의 페이지에서 에러가 나서 연결이 해제되었다고 치자.
    - View의 경우 Reload, Refresh 등을 통해 **Request를 보내줘야 다시 연결** 될 수 있을 것이다.
    - Consumer의 경우 Reload, Refresh등을 통해 **Request를 다시 보내는 일을 하지않아도 자동으로 재연결** 될 수 있다. 일단 접속이 되어있는 상태에서는 **이벤트가 모두 비동기로 일어날 수 있기 때문이다.** 심지어 연결과 해제가 같은 시간에 동시에 이루어질 수도 있다.

<br>

### Websockt 프로토콜을 이용한 채팅 기능을 구현하고 싶을 때 Consumer 예

빌트인 메서드를 이용한다.

```python
class ChatConsumer(AsyncConsumer):

    async def websocket_connect(self, event):
        print("connect", event)

        await self.send({
            "type": "websocket.accept"
        })

        other_user = self.scope['url_route']['kwargs']['<kwargs로 설정한 str>']
        me = self.scope['user']

        # awit로 메서드를 기다려주어야 안정적으로 obj가 입력된다.
        thred_obj = await self.get_thread(me, other_user) 

        await self.send({
            "type": "websocket.send",
            "text": "Hello, World!"
        })
    

    async def websocket_receive(self, event):
        # {'type': 'websocket.receive', 'text': '{"message": "abc"'}의 형태로 받는다고 치자.
        print("connect", event)
        front_text = event.get('text', None)

        if front_text is Not None:
            loaded_dict_data = json.loads(front_text)
            msg = loaded_dict_data.get('mesage')
            print(msg)

            me = self.scope['user']
            username = 'default'

            if user.is_authenticated:
                username = user.username

            myResponde = {
                'message': msg,
                'username': username
            }
            
            # echo 역할을 한다.
            await self.send({
                "type": "websocket.send",
                "text": json.dumbs(myResponse)
            })


    async def websocket_disconnect(self, event):
        print("connect", event)

    
    @database_sync_to_async
    def get_thread(self, user, other_username):
        return Thread.objects.get_or_new(user, other_username)[0]
        # 기존 유저에게 채팅 스레드가 있으면 그것을 가져오고, 없으면 새로운 스레드를 부여하는 모델.

```
(python 3.5 이상이어야 async를 사용할 수 있다.)
- **`websocket_connect()`** : websocket이 **연결됐을 때** 의 행동을 다루는 함수.
- **`websocket_receive()`** : 무언가를 **전달 받았을 때** 의 행동을 다루는 함수. Channel의 매핑으로 인하여 `websocket_receive`라고 이름지으면 **자동으로 websocket type이 receive로 된 것들을 전달 받게된다.**
- **`self.send({})`** : 딕셔너리 형태로 어떤 프로토콜을 이용하여 어떤 응답을 클라이언트 사이드에 보낼지 설정한다.
- **`self.scope[]`** :  scope로 user를 판별할 수 있다.
- **`@database_sync_to_async`** : async로직에서 데이터 베이스에 접근하는 메서드가 있으면 `@database_sync_to_async `데코레이터를 이용하여 비동기로 만들어 준다. async로 계속 비동기로 연결이 되어있다면 데이터 베이스에 접근할 때 과도한 요청이 갈 수 있는데 이 데코레이터는 그것을 방지해준다. **끔찍한 일이 일어나길 바라지않는다면 꼭 사용하자.**

<br><br>

## Routing
자, Consumer을 만들었으니 이제 Routing을 해줘야한다. 
그러나 당연하게도 기존 Http 프로토콜을 사용하는 장고의 Url기능으로는 Routing을 할 수 없다.
하지만 걱정말자. Channel이 알아서 Routing을 해준다.
그저 Channel에 어떤 프로토콜을 어떻게 Routing하고싶은지 알려주기만 하면 된다. 

<br>

### Websocket 프로토콜을 이용할 때의 Routing 예

```python
application = ProtocolTypeRouter({
    'websocket': AllowedHostsOriginValidator(
        AuthMiddlewareStack(
            URLRouter(
                [
                    url(r"^message/(?P<username>[\w.@+-]+)/$", ChatConsumer(사용할 Consumer)
                ]
            )
        )
    )
})
```
- **`websocket`** : 사용하고자 하는 프로토콜을 알려준다. 예제의 경우에는 websocket 프로토콜을 사용하기로 했다.
- **`AllowedHostsOriginValidator()`** : 이름 그대로 허가된 host인지 확인해준다. 어떤 host가 요청을 하던, 어떤 domain 이름이 요청을 하던 일단 그것들이 settings.py에 있는 ALLOWED_HOSTS에 속해있는지 확인해주는 역할을 한다. 
- **`AuthMiddlewareStack()`** : 보안을 위해 프로토콜에 접근하려는 사용자를 확인하는 middleware다.
- **`URLRoter()`** : Routing하고싶은 Url List를 받는다.(기존의 장고 url과 흡사).
    - 원하는 url 주소는 `ws://domain/message/<username>`이며 Websocket url을 Django view와 매치시켜서 render 시키려고 한다.


<br><br><br><br>

## Front에서 Websocket으로 통신을 해서 Channel이 다룰 수 있도록 해보자.

<br>

### HTML 일 때.
js의 socket 메서드를 사용한다.
```html
<script>

console.log(widow.location)
var loc = window.location
var wsStart = 'ws://' 

if (loc.protocol == "https:") {
    wsStart = 'wss://'
}

var endpoint = wsStart + window.loc.host + window.loc.pathname
var socket = new WebSocket(endpoint)
 
socket.onmessage = function(e) {
    console.log("received message", e.data)
    //화면에 받은 메시지를 뿌려주는 코드를 이곳에 작성
}


var formData = //form
var msgInput = //message id
socket.onopen = function(e) {
    console.log("open", e)
    formData.submit(function(event){
        event.preventDefault()

        // 다루기 쉽게 Dictionary 형태로 가공해서 보냄
        var msgText = msgInput.val()
        var finalData = {
            'message' : msgText
        }
        socket.send(JSON.stringfy(finalData)) 

        formData[0].reset()
    })
}

socket.onerror = function(e) {
    console.log("error", e)
}

socket.onclose = function(e) {
    console.log("close", e)
}
</script>
```
- **`endpoint`** : websocket 버전의 URL같은 것이다. wsStart는 https보안을 사용할 때 websocket도 마찬가지로 wss로 바뀌도록하였다.
- **`onmessage`** : 클라이언트 사이드의 socket가 message를 받았을 때의 행위를 다루는 함수.
- **`onopen`** : socket이 오픈되어있을때의 행동을 다루는 함수. 
    - 채팅 form이 submit될 때 새로고침을 막고(preventDefault()), socket에 메시지("form Data value")를 전달하는(socket.send()) 행동을 했다.


<br><br><br><br>

# 프로토콜은 정상작동하지만 채팅기능은 안된다?
여기까지 Channel을 이용하여 Websocket 프로토콜을 장고에서 다루어보았다. 그러나 실제로 채팅기능은 구현되지않는다. 분명히 프로토콜은 잘 작동하지만, 그것을 다른 클라이언트 사이드들이 알 길이 없기때문이다. 오직 채팅을 입력하는 클라이언트 사이드만 Consumer의 에코기능을 이용할 수 있으며 채팅상대는 이벤트가 일어났는지 전혀 모르는 상태이기 때문에 에코기능을 사용할 수 없다. <br>
어떻게 해야 상대방은 이벤트가 일어났다는 것을 알 수 있을까? 궁금하다면 더 나아가보자.


<br><br><br>

Channels은 채팅상대에게 이벤트가 일어난 것을 알려주기위해 메모리 데이터베이스 시스템인 **`Redis`** 를 활용하여 이벤트 발생이 일어났음을 알려줄 수 있다.

- **`Redis`** : 
    - 데이터를 메모리에 키-값 구조로 저장하는 비관계형(No-SQL) 데이터베이스 관리 시스템.
    - 디스크에 저장되지 않고 메모리에 저장되기 때문에
        - 속도가 빠르다.
        - 가볍다.
        - 장애 시,에 유실성이 높음
    - 주로 금방 사라지는 다량의 가벼운 데이터를 저장할 때 많이 쓴다.
        - Cache, Static 등등...

<br><br>

### 순서
<br>

- Redis를 운영체제에 맞게 설치한 뒤 channels_redis를 pip install한다.
    - Redis 서버의 포트는 기본적으로 6379로 설정되어있을 것이다.

```cmd
pip install channels_redis
```


<br>


- settings.py에 CHANNEL_LAYERS를 설정하여 Redis를 사용할 것이라고 알려준다.
```python
CHANNEL_LAYERS = {
    "default": {
        "BACKEND": "channels_redis.core.RedisChannelLayer",
        "CONFIG": {
            "hosts": [("127.0.0.1", 6379)],
            
            # 프로덕션용으로는 아래를...
            # "hosts": [os.enviroment.get('REDIS_URL', 'redis://localhost:6379')] 
        },
    },
}
```

- Chatroom을 만들어서 Redis를 이용한다.

```python
async def websocket_connect(self, event):
        ###
        ### . . . thread_obj를 받은 상태
        ###

        chat_room = "thread_{thread_obj.id}".format()
        self.chat_room = chat_room

        await self.channel_layer.group_add(
            chat_room,
            self.channel_name
        )

        await self.send({
            "type": "websocket.send",
            "text": "Hello, World!"
        })
    
```
- self.channel_layer.group_add() : Chatroom을 생성하고 그룹을 추가해준다. 여기서는 최근 유저의 채널을 Chatroom에 추가해주었다.
    - channel_name을 특정해주면(unique) 그들만의 챗룸이 만들어진다.
    - channel_name을 특정해주지 않으면 모든 유저
    - channel_name은 CHANNEL_LAYER를 세팅해둬야 나온다. 


```python
async def websocket_receive(self, event):
        #
        # ...myResponse까지...
        #

            await self.channel_layer.group_send(
                self.chat_room,
                {
                    "type": "chat_message",
                    "text": json.dumps(myResponse)
                }
            )
            
            # 아래의 echo를 삭제한다.
            # await self.send({
            #    "type": "websocket.send",
            #    "text": json.dumbs(myResponse)
            # })
```
- self.channel_layer.group_send() : 연결됐을 때의 chat_room을 가져와서 그 그룹에 속해있는 전체에게 응답을 보내는 메서드. 하지만 여기서는 응답을 직접적으로 보내지 않고 알리기만 할 뿐. 직접적인 전송은 다른 메서드에 맡길 것이다.


```python
async def chat_message(self, event):

                print('message', event)
                await self.send({
                    "type": "websocket.send",
                    "text": event['text']
                })
```
- chat_message() : channels나 websocket 메서드가 아닌 임의적으로 만든 메서드로 이 메서드가 실제로 메시지를 응답한다.


- 서버가 닫히면 Websocket의 연결이 끊겨버리는데 이것을 쉽게 재연결 할 수 있는 Reconnecting-websocket 을 설치(https://github.com/joewalnes/reconnecting-websocket)하여 활용해도 좋다.

- 내용을  DB에 어떻게 저장할까?

```python
async def websocket_connect(self, event):
    ### thread obj 불러오고..
    
    self.thread_obj = thread_obj
    
    ### go on..

async def websocket_receive(self, event):
    ### myResponse 설정하고...
    
    await self.create_chat_message(msg)
    self.thread_obj = thread_obj

    ### go on...

@database_sync_to_async
    def create_chat_thread(self, msg):
        thread_obj = self.thread_obj
        me = self.scope['user']
        return Chatmessage.objects.create(thread=thread_obj, user=me, message=msg)
        # 스레드(외부키), 유저(외부키), 메시지, timestamp로 구성된 간단한 모델
```
- create_chat_thread() : `@database_sync_to_async` 데코레이터를 이용하여 새로운 스레드 DB를 생성한다. 