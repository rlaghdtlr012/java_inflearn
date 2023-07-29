package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    public static SingletonService getInstance() {
        return instance;
    }

    // 다른 곳에서 인스턴스를 생성 못하게 private 키워드를 통한 생성자 초기화
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
