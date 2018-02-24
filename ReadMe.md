# SoMap

## 1. __설명__

- 주차된 자동차의 위치 및 자동차 정보 앱
- 구글맵 + 서버 + mysql 학습 목적의 프로젝트

## 2. __Application Structure 및 동영상 link__

- 구글맵에 뿌려지는 데이터들과 해당 데이터들의 정보로 구성
- [시연영상 Link](https://youtu.be/gtqrE7vC7Eo)


## 3. __기능 설명__

- 지역 분류 기능
- 지도에 아이템들이 표시되는 마커기능과 이를 클러스터로 묶는 기능

## 4. __주요 Issue__

### 4-1. 통신 이슈

#### _how to solve_?

- retrofit2 library사용(+ rx안드로이드)

```Java
 // Zone 데이터를 가져오는 함수
    public static void getZones(String area, Callback callback) throws Exception{
        // 레트로핏 정의
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.SERVER_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        // 2. 서비스 만들기 < 인터페이스로부터
        IZone service = retrofit.create(IZone.class);
        // 3. 옵저버블(Emitter) 생성
        Observable<Zone> observable = service.getData(area);

        // 4. 데이터 가져오기
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        zone -> {
                            if(zone.isSuccess()){
                                data = zone.getData();
                                callback.init();
                            }
                        }
                );
    }

    // 인터페이스 생성
    public interface IZone {
        @GET("zone/{param1}")
        Observable<Zone> getData(@Path("param1") String area);
    }
    public interface Callback {
        void init();
    }
```
### 4-2. 구글맵 사용이슈 

#### _how to solve_?

- 구글 맵 내부 api인 ClusterManager를 사용함.


## 5. __Retrospect__

- 구글맵의 다양한 기능들 중 일부분만 다뤄본게 조금 아쉽다. 추후에 좀더 다양한 기능들을 하는 프로젝트를 진행해볼 것이다.
- MySql(관계형 데이터베이스) 모델링 설계+ 문법 사용해보았다.
- 간단한 앱이지만 안드로이드(구글맵,Rx)와 서버, 디비를 다뤄보는 프로젝트이다 생각해볼 부분이 꽤 많았다.  