import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class Main {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 1000 различных пользователей
    private static final int USERS = 1000;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redis = new RedisStorage();
        redis.init();
        // бесконечный цикл
        for(int seconds=0; seconds <= 100; seconds++) {
            seconds--;
            // Выполним 500 запросов
            for(int request = 0; request <= RPS; request++) {
                int user_id = new Random().nextInt(USERS);
                redis.logPageVisit(user_id);
                Thread.sleep(SLEEP);
            }
            //выбираем кого показывать на главной странице из 1000 пользователей
            int showRegularUserId = (int) (Math.random() * USERS);

            // в 1 из 10 случаев пользователь оплачивает показ без очереди
            if (Math.random() * 100 > 10) {
                String log = String.format("[%s] На главной странице показываем пользователя " + showRegularUserId, DF.format(new Date()));
                out.println(log);
            } else {
                int showPaidUserId = (int) (Math.random() * USERS);
                out.println("Пользователь " + showPaidUserId + " оплатил платную услугу ");
                String log = String.format("[%s] На главной странице показываем пользователя " + showPaidUserId, DF.format(new Date()));
                out.println(log);
            }
            redis.deleteOldEntries(DELETE_SECONDS_AGO);
        }
        redis.shutdown();
    }
}
