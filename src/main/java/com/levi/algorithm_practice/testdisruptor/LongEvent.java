package com.levi.algorithm_practice.testdisruptor;

public class LongEvent {
    private String uuid;

    private Long value1;
    private Long value2;



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public Long getValue2() {
        return value2;
    }

    public void setValue2(Long value2) {
        this.value2 = value2;
    }

    public Long getValue1() {
        return value1;
    }

    public void setValue1(Long value1) {
        this.value1 = value1;
    }
    /*
    * def  manachersAlgorithm(s):
    N = len(s)
    str = "#" + "#".join(s) + "#"
    leng = (2 * N) + 1
    P = [0] * leng
    c = 0 # stores the center of the longest palindromic substring until now
    r = 0 #stores the right boundary of the longest palindromic substring until now
    maxLen = 0
    for i in range(leng):
        # get mirror index of i
        mirror = (2 * c) - i

        # see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
        # if it is, then take r - i as P[i]
        # else take P[mirror] as P[i]
        if i < r:
            P[i] = min(r - i, P[mirror])
        //expand at i
        a = i + (1 + P[i])
        b = i - (1 + P[i])
        while a < leng and b >= 0 and str[a] == str[b]:
            P[i] += 1
            a += 1
            b -= 1

        #check if the expanded palindrome at i is expanding beyond the right boundary of current longest palindrome at center c
        #if it is, the new center is i
        if i + P[i] > r:
            c = i
            r = i + P[i]

            if P[i] > maxLen: #update maxlen
                maxLen = P[i]

    return maxLen
    *
    *
    *



    * */
}
