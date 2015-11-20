xs = [
  [0,0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0,0],
  [0,0,0,1,2,0,0,0],
  [0,0,0,2,1,0,0,0],
  [0,0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0,0]
]

def xs_to_s(xs)
  stdin = xs.map{|x|x.join(" ")}.join("\n")
  stdout = ""
  IO.popen(["ruby", "show.rb"], "r+"){|io|
    io.puts stdin
    io.close_write
    stdout = io.read
  }
  return stdout
end

def detect_pos(xs,turn)
  stdin = xs.map{|x|x.join(" ")}.join("\n")
  stdout = ""
  IO.popen(["ruby", "dets.rb", turn.to_s], "r+"){|io|
    io.puts stdin
    io.close_write
    stdout = io.read
  }
  return stdout.split("\n").map{|l| l.split.map{|i|i.to_i} }
end

def replace(xs,turn,x,y)
  stdin = xs.map{|x|x.join(" ")}.join("\n")
  stdout = ""
  IO.popen(["ruby", "puts.rb", turn.to_s, y.to_s, x.to_s], "r+"){|io|
    io.puts stdin
    io.close_write
    stdout = io.read
  }
  return stdout.split("\n").map{|l|l.split.map{|i| i.to_i}}
end

def count(xs)
  stdin = xs.map{|x|x.join(" ")}.join("\n")
  stdout = ""
  IO.popen(["ruby", "count.rb"], "r+"){|io|
    io.puts stdin
    io.close_write
    stdout = io.read
  }
  return stdout.split("\n").map{|i|i.to_i}
end


turn = 1
passc = 0
while true
  puts xs_to_s(xs)
  puts "Turn: #{turn == 1 ? "o" : "x"}"
  c = count(xs)
  puts "o:#{c[0]}  x:#{c[1]}"
  dets = detect_pos(xs, turn)
  if dets.length > 0
    puts "You can put at:"
    passc = 0
    dets.each.with_index{|(i,j),idx|
      puts "  [#{idx+1}] (#{i},#{j})"
    }
    while (i = gets.chomp.to_i) == 0 || i <= 0 || i > dets.length; end
    xs = replace(xs, turn, dets[i-1][1], dets[i-1][0])
  else
    puts "You can't put anywhere."
    passc += 1
  end
  if passc >= 2
    break
  end
  turn = turn == 1 ? 2 : 1
end

c = count(xs)
if c[0] > c[1]
  puts "'o' win"
elsif c[1] > c[0]
  puts "'x' win"
else
  puts "draw"
end
