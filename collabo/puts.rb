turn = ARGV[0].to_i
y = ARGV[1].to_i
x = ARGV[2].to_i

xs = STDIN.each_line.to_a.map{|line|
  line.split.map{|i|i.to_i}.push(3).unshift(3)
}.push([3,3,3,3,3,3,3,3,3,3]).unshift([3,3,3,3,3,3,3,3,3,3])


def replace(turn,xs,x,y,dx,dy,c)
  if c > 0 && xs[y][x] == turn
    for d in 1..(c+1)
      xs[y-dy*d][x-dx*d] = turn
    end
    return
  end
  e = turn == 1 ? 2 : 1
  return xs[y][x] == e && replace(turn, xs, x+dx, y+dy, dx, dy, c+1) 
end


[-1,0,1].product([-1,0,1]).select{|dx,dy|
  dx != 0 || dy != 0
}.each{|dx,dy|
  replace(turn, xs, x+dx, y+dy, dx, dy, 0)
}

puts xs[1,8].map{|x|x[1,8].join(" ")}.join("\n")
