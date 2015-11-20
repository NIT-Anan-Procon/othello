

if ARGV.length > 0
  case ARGV[0].chomp
  when "author"
    puts "Takeshi Kojima"
  when "name"
    puts "MINMAX"
  when "description"
    puts "The simple AI based on Min-Max"
  end
  exit
end
