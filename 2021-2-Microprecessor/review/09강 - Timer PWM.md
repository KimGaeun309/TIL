# 9강 - Timer PWM

이번 시간에는 타이머를 이용해 외부 출력 신호를 제어하는, 외부 혹은 내부에  우리가 원하는 값을 내보내는 실습을 해보겠다.

## 타이머와 PWM

지난 시간에는 타이머의 개념과 그를 이용한 시간을 generation 하는 기본 원리를 알아보았다. 이번 시간에는 이 타이머라는 컨트롤러를 이용해 다른 용도로도 활용할 방법이 많은데 그 중에서 Pulse Width Modulation 이라는 전자 신호를 타이머를 이용해서 어떻게 설정을 하고 응용하는지 알아보도록 하겠다.

## PWM(Pulse Width Modulation)

우리가 전자적인 신호라고 하는 것은 시간의 흐름 상에 값의 변화가 일어나는 신호가 나갈 때 0 또는 1이 되는 것(?). 

### 펄스(Pulse)와 펄스폭(Pulse Width)

이 때 펄스는 1이 나가는 신호이고,  펄스폭은 신호가 나가는 폭을 말한다. 

### PWM(펄스폭변조)

PWM은 펄스폭을 조정하는 것을 말한다.

각 주기마다 펄스가 나가는데 이 펄스가 나가는 시간을 매 주기마다 조절해줄 수 있는 신호를 PWM 이라고 한다. 예를 들어서 주기가 있고 각 주기마다 주기의 시간에서 10%만 계속 신호를 내보내면 신호의 총량은 10% 정도 나가는 것으로 보여질 수 있다. 이런 식으로 신호의 세기를 조절할 수 있다!! 신호의 세기를 조절함으로서 원하는 장치에 원하는 만큼의 크기를 제공할 수 있다. 

예를 들어 마이크로프로세서에서 하나의 핀으로 신호를 내보내는데 이 신호를 PWM으로 조절함으로써 이 신호의 세기를 조절해줄 수 있다. 이를 통해 여러가지 응용을 할 수 있다. PWM이라고 할 때 주기라는 것이 있고 이 한 주기에서 얼마만큼의 펄스폭으로 내보내는지(=비율)가 있는데 이 두 가지를 조정할 수 있는 컨트롤러가 필요한데 Timer를 사용해 이를 조절할 수 있다. (그런 기능을 Timer가 가지고 있다.) 따라서 이번에는 타이머를 이용해 외부의 아날로그 장치에 우리가 원하는 만큼의 세기로 신호를 내보내는 실습을 해볼 것이다.

## Capture input / Compare output

그를 위해 기본적으로 타이머를 이용해서 PWM 신호를 조절하는데, 구체적으로 각각의 마이크로 프로세서는 구현된 사양이 달라서 어떤 프로세서를 이용해 특정한 기능을 부여하거나 만들기 위해서는 레지스터 영역을 이해를 해야 한다.

Cortex-M3 의 Timer의 PWM 구현을 위한 구현이 어떻게 되어 있는지 살펴 보자.

지난 시간에도 말했듯, Cortex-M3의 Timer에는 네 가지 기능이 있다. 외부 시간을 생성하거나 인터럽트를 발생시키는 타이머 기능, 특정 시간에 대한 카운팅을 할 수 있는 카운팅 기능, 그리고 input catpure 기능과 output compare 기능이 있다. 오늘은 뒤의 두 가지 기능에 대해서 알아볼 것이다.

### Compare mode

input capture 와 output compare 는 반대되는 개념이다. output compare먼저 말하자면, 원하는 신호를 내보내는데 시간을 고려해서 특정 시간만큼 0 또는 1을 내보낼 수 있는 기능이다. 이 때 PWM은 특정 주기가 있고 각 주기마다 펄스폭을 조절할 수 있는 기능이 된다.

### Capture input

반대로 capture input은 이게 반대로 들어온다고 생각하면 된다. 0 또는 1의 신호가 얼마만큼의 시간만큼 지속되는지 측정할 필요가 있는 경우. 어떤 시간동안 1이 얼마나 들어오는지, 0이 얼마나 들어오는지를 측정해 외부에서 들어온 신호의 길이를 측정할 수 있다. PWM도 주기마다 반복되면서 조절되는 시간이 있는 경우 각 주기마다 펄스폭이 얼마인지를 알아낼 수 있는 기능을 한다. 

이러한 기능들이 있는데 우리는 그 중에서 Compare mode 중에서 PWM mode 에 대해 실습을 할 것이다.

이번에는 중간에 Shadow 레지스터와 CCR 레지스터를 추가로 사용할 것이다. PWM 주기는 지난 시간에 했던 ARR을 사용해 주기를 만들어내고, 동일한 주기마다 변조되는 펄스폭은 CCR 레지스터에 값을 넣어 매 주기마다 폭을 바꾸어줄 수 있다. 매 주기마다 같은 값을 넣어주면 동일한 Pulse Width가 나가는 것이고, 다른 값을 넣어주면 변조된 Pulse Width가 나가는 것이다.

## Capture / Compare mode

기본적으로 preload register(ARR 에 해당)가 있고 Counter 레지스터가 있다. 이것을 토앻 주기를 만들어낸다. 그리고 CCR 레지스터에 어떤 값을 넣어 원하는 만큼 1을 내보내고 그 다음 ARR에 도달할 때까지 0을 내보낸다. 또 CCR 값까지 1 내보내고 ARR 도달할 때까지 0을 내보낸다. 

예를 들어 ARR이 8이고 CCR 이 4이면 Counter register가 0, 1, 2, 3 동안 1이 나가고, 4, 5, 6, 7 동안 0이 나간다.

CCR이 8보다 크면 계속 1이 나가고 0이면 계속 0이 나간다.

## Capture/Compare 관련 Register

### TIMx_CCMR1, TIMx_CCMR2

먼저 타이머는 네 개가 있고 모든 타이머는 타이머기능, 카운터 기능, 캡처 인풋 기능, 컴페어 아웃풋 기능 (4가지 기증)이 있다.

 CCxS에 00을 넣으면 o utput 모드, 나머지는 input 모드. 우리는 output으로 사용할 것이니 00으로 설정하면 된다.

- OCxPE 와 TIMx_CR1

### TIMx_CCRx

그 다음 CCR 레지스터는 얼마만큼 1을 내보낼지를 결정하는 레지스터. 여러가지 모드에 대한 CCR 레지스터 값이 있다. output 모드로 설정되었다면 내보내는 값이 되고, input 모드로 설정되었다면 캡처하는 값으로 사용한다.

### TIMx_CCER

CCR 값까지 1을 내보내고 ARR 값까지 0을 내보내는 것만 말했는데, 반대로 CCR까지 0, ARR까지 1을 내보낼 수도 있다. CCER 레지스터는 타이머 1, 2, 3, 4 각각에 대해서 output으로 ccr까지 내보낼 값이 0일지 1일지 결정할 수 있다. 이것이 0이면 ccr 까지 1을 내보내고, 1이면 ccr까지 0을 내보낸다. 우리는 ccr까지 1을 내보내기 때문에 이것에 0을 쓸 것이다. 

CCRx와 CCER, CCMR → output으로 설정, 0으로 설정, .. 이렇게 세 가지만 설정해주면 CCR과 관련되어 Pulse width를 얼마만큼 내보낼지를 다 설정해준 것. 그 다음으로 주기를 설정해주어야 하는데 주기는 ARR을 통해 지난 시간과 같이 설정해주면 된다.

### TIMx_EGR

EGR 레지스터는 값을 내보내거나 값이 인풋 캡처로 들어왔을 때 = 끝나는 이벤트가 발생했을 때 사용할 수 있다. (잘 몰라도 상관X)

여기까지가 기본 개념이었다.

정리를 하자면, 타이머를 이용해서 PWM이라는 외부 신호를 생성해서 내보낼 수 있는데, 그러기 위해서 ARR 레지스터를 통해 주기를 생성해주고 CCR 레지스터를 통해 PW를 조절해주어야 한다.

 counter register가 증가를 하면서 1을 내보내고 ccr에 도달하면 arr에 도달할 때까지 0을 내보내는 식으로 동작한다. 그러면 한 주기가 끝난다. 그리고 다음 주기에서 ccr 값을 변경시키면 변경된 ccr 값에 맞추어 변조된 폭을 가지는 신호를 내보내도록 제어할 수 있다.

## 16비트 타이머/카운터의 동작모드

동작을 하는데 정리를 하면 CCR 레지스터로 정의한 듀티비 = Pulse width 와 ARR 레지스텆로 정의한 주기.

CCMR 레지스터는 각 타이머를 output으로? input으로? 결정.

PWM 동작을 위해서는 CCMR의 OCxPE 와 TIMx_CR1의 비트를 enable해야 한다. 

그 다음으로 ARR 레지스터에 주기를 쓴다.

CCR 레지스터에 값을 쓰고

CCER에 1을 써 카운터를 enable시키면 시작한다.

이 내용은 추가자료의 general purpose timer에 다 설명이 되어 있다. 14.2 에 기능이 나와 있다. 14.3.8, 14.3.9 에 output 모드에 대한 설명이 들어있다. 14.4 에 레지스터 관련 내용이 나오는데, 14.4.7, 14.4.8 에 enable하는 값 등 여러가지 내용이 나온다. enable하는 값 등 여러가지 그다음 14.4.9dp CCER에 폴라리티? 설정과 compare를 enable하는 것을 설정 가능. 그리고 14.4.11에 ccr레지스터 내용이 있다. 

## 추가

여기에 실습 전에 설명을 더 추가로 하면 외부 신호를 내보내는 것이니까 GPIO를 써야 하는데, GPIO 핀 중 하나를 연결을 해서 외부 신호로 나가도록 해야 한다. 이 때 지금 사용하는 Timer를 사용해서 PWM 신호를 내보내는 것은 GPIO 임의의 핀을 General purpose 용도로 사용하는 것이 아니라 PWM용도, 특정 용도로 사용하는 것이다. 그럴 경우 이 핀을 GPIO의 output 핀으로 설정해주면 안되고, Alternative Function 핀으로 설정해주어야 한다. 일반적으로 우리가 핀을 내보낼 때 GPIO 용도로 사용할 수 있게 한다. 하지만 이 핀들은 GPIO로만 사용할 수 있는 것이 아니라, 어떤 특정 핀에 특정 기능을 연결하도록 (=한정된 기능을 수행하도록) 핀을 설정할 수 있다. 모든 기능에 대해 핀을 설정하면 핀이 너무 많아지기 때문에 핀에 여러가지 기능을 부여해둠. 이 핀들을 특정 기능으로 설정해줄 수 있다. 우리가 GPIO 를 보면 - 8.1  보면 어떤 핀들은 alternative function으로 설정할 수도 있다는 것을 알 수 있다. 특정 핀은 pwm 신호로 내보내도록 정해주어 AFIO로 사용해야 함. Alternative Function Input Output.. 8.1.11 을 보면 다 정해져 있다. Timer 중에서 output으로 쓰려면 general purpose timer 의 alternative function input output으로 활용하려면 8.3.7 을 보면 TIM3의 CH1을 써야 하는데 이것은 GPIOA의 여섯 번째 핀이다.

# 실습 1 : PWM으로 LED 밝기 조절

# 실습 2 : 타이머로 버저울리기