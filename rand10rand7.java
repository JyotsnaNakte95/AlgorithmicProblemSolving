/**
 * The class finds rand10() using ran7().
 * @author Jyotsna Nakte
 */
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class rand10rand7 extends SolBase {
    public int rand10() {          
        while(true) {
            int index = rand7() + (rand7() - 1) * 7;
            if(index <= 40) {
                return  1 + ((index - 1) % 10);
            }
        }
    }
}


