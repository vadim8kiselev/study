#                                INPUT
###########################################################################
# n = int(input())  # enter int value
#
# array = [int(i) for i in raw_input().split(' ')]  # enter int row
#
# array = [i for i in raw_input().split(' ')] // or str row-list
#
# a, b = raw_input().split() // or two str values
###########################################################################

#                       CYCLES AND CONDITIONS IN A NUTSHELL
###########################################################################
# CYCLE FOR:
# for i in range(5):  # // range(n) is 0,1,2...n-1
#     print i,        # // is 0 1 2 3 4 (IN A ROW, NOT COLUMN)
# else:
#     print "for ended here!"


# CYCLE WHILE:
# a = 5
# while a:
#     print a,
#     a -= 1
# else:
#     print "while ended here!"


# CONDITION IF/ELIF/ELSE
# val = 5
# if val > 2:
#    print "val > 2"
# elif val < 2:
#    print "val < 2"
# else:
#    print "val == 2"

###########################################################################

#                                SUM ELEMENTS
###########################################################################
# print int(a)+int(b) // we can sum two element
# print sum(array) // or sum all list
# print sum([i for i in array]) // another solution
# print sum([array[i] for i in range(len(array))]) // another solution
# print sum([array[i] for i in xrange(len(array))]) // another solution
#
#                                WITH CONDITION
###########################################################################
# print sum([i for i in array if not i % 2]) // also we can sum only even values
# print sum([i for i in array if i > 3]) // or if element more than key (3)
# print sum([array[i] for i in xrange(n) if (i+1) & i == 0]) // sum elements which stand on x^2 places
############################################################################
#
############################################################################


#                                SEARCH MIN/MAX
###########################################################################
# print array.index(min(array)) // now we searched index of first min in a row
# copy = list(reversed(array)); print len(copy)-1-copy.index(min(copy)) // or the last max in a row
############################################################################

