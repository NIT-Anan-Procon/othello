turn = ARGV[0].to_i

xs = STDIN.each_line.to_a.map{|line|
  line.split.map{|i|i.to_i}.push(3).unshift(3)
}.push([3,3,3,3,3,3,3,3,3,3]).unshift([3,3,3,3,3,3,3,3,3,3])


def check(turn,xs,x,y,dx,dy,c)
  return true if c > 0 && xs[y][x] == turn
  e = turn == 1 ? 2 : 1
  return xs[y][x] == e && check(turn, xs, x+dx, y+dy, dx, dy, c+1) 
end

(1..8).to_a.product((1..8).to_a).select{|i,j|
  xs[i][j] == 0
}.select{|i,j|
  [-1,0,1].product([-1,0,1]).select{|dx,dy|
    dx != 0 || dy != 0
  }.any?{|dx,dy|
    check(turn, xs, j+dx, i+dy, dx, dy, 0)
  }
}.each{|i,j|
  puts "#{i} #{j}"
}
