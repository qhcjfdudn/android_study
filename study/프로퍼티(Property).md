# 프로퍼티(Property)

프로퍼티란? 클래스의 변수. var, val로 선언되는 변수들.(var, val로 선언되지 않은 매개변수들은 프로퍼티가 아니다.)

프로퍼티란 왜 **변수가 아닌 프로퍼티**라 부르는가?
: **함수가 내장된 변수이기 때문**. 접근자(access)라 부르는 getter와 setter가 내장되어 있다. 직접 정의할 수도 있다.

```kotlin
형식
var <perpertyName>[: <PropertyType>] = [= <property_initializer>]
[<getter>]
[<setter>]
```

프로퍼티는 var나 val로 선언하며, 이름과 데이터 타입, 초깃값을 명시할 수 있다. 그 안에 getter, setter함수를 정의 가능. 선언할 때 초깃값과 getter, setter는 선택 사항. 데이터타입도 추론 가능하다면 선택 사항.

아래와 같이 직접 추가해줄 수 있다. 이 때 field는 프로퍼티에 저장된 값 자체를 사용하는 예약어. get(), set() 함수에서만 사용할 수 있다. 클래스 외부에서는 .변수명 을 통해서 get(), set()에 접근하지만, get(), set()을 사용할 때는 field를 통해 프로퍼티가 갖는 값에 접근할 수 있다. 이를 backing field라 한다.

```kotlin
class User {
    var name: String
    	get() = field
    	set(value) { field = value }
}
```



여기서 잠깐!

**캡슐화** : 클래스의 내부 변수는 private으로 선언하고, 대신 getter, setter 메서드를 통해 변수를 이용하도록 하는 것. 변수를 캡슐로 묶어 외부에서 직접 이용하지 못하게 보호하고, 접근할 수 있는 메서드를 대신 사용하도록 하는 개념. 이를 통해 외부에서는 실제 클래스 내부 데이터가 어떻게 바뀌는지 알지 못하므로, **정보은닉**을 달성한다.



프로퍼티는 코드에서 변수를 직접 이용하는 것**처럼** 보이지만, 실제로는 **변수에 내장된 getter, setter를 이용한다.** 이렇게 함으로써 **외부에서는 변수를 직접 이용**하고, **불필요한 getter/setter 함수를 만들지 않아**도 된다. 필요에 따라 getter/setter를 직접 정의해주면 됨! 이는 유지보수에도 도움이 된다.



