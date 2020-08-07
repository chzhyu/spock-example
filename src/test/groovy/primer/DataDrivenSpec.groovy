package primer


import spock.lang.Specification
import spock.lang.Unroll

/**
 * Description: 
 *
 *
 * <pre>
 * ================= 维护日志 =================  
 * 2020-08-07 16:04 chenzhenyu4 新建代码 
 * ================= 维护日志 =================
 * </pre>
 * @author chenzhenyu4* @date 2020-08-07 16:04
 * @version 1.0.0
 */
class DataDrivenSpec extends Specification {

    @Unroll
    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 2 || 2
        1 | 3 || 3
        2 | 3 || 2
    }

}