xs = STDIN.each_line.to_a.map{|line|
  line.split.map{|i|i.to_i}.map{|i|
    i == 1 ? "o" : i == 2 ? "x" : " "
  }
}

puts "+--------+"
puts xs.map{|x|"|#{x.join}|"}.join("\n")
puts "+--------+"


