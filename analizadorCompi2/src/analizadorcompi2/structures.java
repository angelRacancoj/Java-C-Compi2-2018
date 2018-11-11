/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorcompi2;

/**
 *
 * @author angel
 */
public class structures {
    /*
    if simple:

        if(boolean){
            something();
        }

        if(boolean) goto lf1
        goto lf2

        lf1:
            something:

        lf2:
        continue the program...

    if-else simple

        if(boolean){
            something();
        } else {
            somenhing2();
        }

        if(boolean) goto lf1
        goto lf2

        lf1:
            something:
            goto lf3

        lf2:
            something2:

        lf3:
        continue the program...

    if-else if-else (n "else if")

        if(boolean){
            something();
        } else if(boolean2){
            something2();
        } else {
            somenhing3();
        }

        if(boolean) goto lf1
        goto lf2

        lf1:
            something:
            goto lf3

        lf2:
        if(boolean2) goto lf4
        goto fl5

        lf4:
            something2:
            goto fl3

        lf5:
            something3:

        lf3:
        continue the program...

     */

 /*
    while strcture

    while(boolean){
        something();
    }

    lw1:
    if (boolean) goto lw2
    goto lw3

    lw2:
        something:
    goto lw1

    lw3:
    continue the program...
     */
 /*
    Array declaration en select an element

    Array of 1 dimension
        int array[n];

        array[n]

    Select from Array of 1 dimension

        x = array[m];

        x = array[m]

    Array of n dimensions
        int array[x][y][z];

        ;-> tam1 = x
        ;-> tam2 = y
        ;-> tam3 = z

        d1 = x * y
        d2 = d1 * z
        array[d2]

    Select from Array of 1 dimension

        x = array[f][g][h];

        ;-> pos1 = f
        ;-> pos2 = g
        ;-> pos3 = h
        ; Formula -> (y*z*f)+(z*g)+h

        d3 = tam2 * tam3
        d4 = d3 * pos1
        d5 = tam3 * pos2
        d6 = d4 + d5
        d7 = d6 + pos3
        x = array[d7];

        list[a][b][c][d];

        y = list[w][x][y][z];

        d1 = a * b
        d2 = d1 * c
        d3 = d2 * d
        list[d3]

        ; (b*c*d*w)+(c*d*x)+(d*y)+z

        d4 = b * c
        d5 = d4 * d
        d6 = d5 * w
        d7 = c * d
        d8 = d7 * x
        d9 = d * y
        d10 = d6 + d8
        d11 = d10 + d9
        d12 = d11 + z

        y = list[d12]

     */
 /*
    if(x > 100)&&(a < b || a != x)&&(y+1 < 5){
        something();
    }

    s1 = y + 1

    if(x > 100) goto lb1
    goto lb2

    lb1:
        if(a < b)goto lb4
        goto lb5

        lb4:
            if(s1 < 5) goto lb6
            goto lb7

            lb6:
                b1 = 1
                goto lb3
            lb7:
                b1 = 0
                goto lb3

        lb5:
            if(a != x)goto lb8
            goto lb9

            lb8:
                goto lb4
            lb9:
                goto lb2

    lb2:
    b1 = 0

    lb3:
        if(b1 == 1) goto lf1
    goto lf2

    lf1:
        something:
    lf2:
        continue the program...


    bool feliz = ((x+4)*10 < 40/y) || (z == y && (x-90) < a) || (x+y < b*z);

    d1 = x + 4
    d2 = d1 * 10
    d3 = 40 / y

    if(d2 < d3)goto lb1
    goto lb2

    lb1:
        b1 == 1
    goto lb3
    lb2:
        if(z == y)goto lb4
        goto lb5

        lb4:
            d4 = x - 90
            if(d4 < a)goto lb6
            goto lb7

            lb6:
                b1 = 1
                goto lb3
            lb7:
                d5 = x + y
                d6 = b * z

                if(d5 < d6)goto lb9
                goto lb10
                lb9:
                    b1 = 1
                    goto lb3
                lb10:
                    b1 = 0
                    goto lb3
        lb5:
            goto lb7

    lb3:
    feliz = b1

    continue the program
     */
}
