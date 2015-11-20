xs = STDIN.each_line.to_a.map{|line|
  line.split.map{|i|i.to_i}
}
puts (0..7).to_a.product((0..7).to_a).inject([0,0]){|(a,b), (i,j)|
  xs[i][j] == 1 ? [a+1,b] : xs[i][j] == 2 ? [a,b+1] : [a,b]
}

