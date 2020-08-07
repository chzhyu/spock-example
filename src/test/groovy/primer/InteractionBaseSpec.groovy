package primer

import spock.lang.Specification


/**
 * Description: 
 *
 *
 * <pre>
 * ================= 维护日志 =================  
 * 2020-08-07 17:24 chenzhenyu4 新建代码 
 * ================= 维护日志 =================
 * </pre>
 * @author chenzhenyu4* @date 2020-08-07 17:24
 * @version 1.0.0
 */
class InteractionBaseSpec extends Specification {

    def publisher = new Publisher()

    def subscriber1 = Mock(Subscriber)
    def subscriber2 = Mock(Subscriber)

    void setup() {
        publisher.subscriberList << subscriber1
        publisher.subscriberList << subscriber2
    }

    def "push_msg_to_subscriber"() {

        when:
        publisher.fire(msg)

        then:
        1 * subscriber1.receive(msg)
        _ * subscriber2.receive(msg)

        where:
        msg = "msg"
    }
}

interface Subscriber {
    void receive(String msg);
}

class Publisher {
    List<Subscriber> subscriberList = []

    void add(Subscriber subscriber) {
        subscriberList.add(subscriber)
    }

    void fire(String msg) {
        subscriberList*.receive(msg)
    }


}