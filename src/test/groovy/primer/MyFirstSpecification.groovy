package primer


import spock.lang.Shared
import spock.lang.Specification

/**
 * Description:
 *
 *
 * <pre>
 * ================= 维护日志 =================
 * 2020-08-07 11:47 chenzhenyu4 新建代码
 * ================= 维护日志 =================
 * </pre>
 * @author chenzhenyu4* @date 2020-08-07 11:47
 * @version 1.0.0*
 */
class MyFirstSpecification extends Specification {

    @Shared
    String name;

    def setupSpec() {
        println 'setSpec'
    }

    def setup() {
        println 'setUp'
        name = 'setupName'
    }

    def cleanupSpec() {
        println 'cleanup spec'
    }

    def cleanup() {
        println 'cleanup'
        name = 'cleanupName'
    }


    def 'pushing an element on stack'() {
        given: 'stack and push element'
        def stack = new Stack()
        def elem = 'push me'

        when: 'push'
        stack.push(elem)

        then: 'assert'
        !stack.empty
        stack.size() == 1
        stack.peek() == elem

        println name
    }

    def 'pop an element from a empty stack'() {

        given: 'an empty stack'
        def stack = new Stack();

        when:
        stack.pop()

        then:
        EmptyStackException e = thrown()
        e.cause == null

    }

    def 'Hashmap accept null key'() {

        given:
        def map = new HashMap();

        when:
        map.put(null, 'value')

        then:
        notThrown(NullPointerException)

    }

    def 'event are published to all subscribers'() {
        given:
        def publisher = new Publisher()
        def subscriber1 = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        publisher.add(subscriber1)
        publisher.add(subscriber2)

        when:
        publisher.push('event')

        then:
        1 * subscriber1.onEvent('event')
        1 * subscriber2.onEvent('event')

    }


    class Publisher {
        private List<Subscriber> subscriberList = new ArrayList<>()

        void add(Subscriber subscriber) {
            subscriberList.add(subscriber)
        }

        void push(String event) {
            subscriberList.each { it.onEvent(event) }
        }


    }


    class Subscriber {
        void onEvent(String event) {
        }
    }

    def "expect"() {
        expect:
        Math.max(1, 2) == 2
    }

    def "cleanup block"() {
        given:
        def file = new File("testFile")
        file.createNewFile()

        cleanup:
        file?.delete()
    }

    @SuppressWarnings('GroovyAssignabilityCheck')
    def 'where block'() {
        expect:
        Math.max(a, b) == c

        where:
        a << [1, 2, 3]
        b << [3, 2, 1]
        c << [3, 2, 3]
    }

    def "with"() {
        when:
        def p = new Person()

        then:
        with(p) {
            name == 'default'
            gender == 'unknown'
        }
    }

    def "verifyAll"() {
        when:
        def p = new Person()

        then:
        verifyAll(p) {
            name == 'aefault'
            gender == 'anknown'
        }
    }

    class Person {
        String name = 'default'
        String gender = 'unknown'
    }

    def "doc"() {
        given: 'open a connection'
        //
        and: 'insert a record into mysql'
        //
        when: 'query the recode'
        then: 'verify the id'
        and: 'verify the created date'

    }
}
