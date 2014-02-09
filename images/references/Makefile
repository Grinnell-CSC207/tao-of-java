mem := $(shell ls *.mem)
svg := $(mem:%.mem=%.svg)
png := $(mem:%.mem=%.png)

svg: $(svg)
png: $(png)

%.svg: %.mem 
	mem2svg < $< > $@

%.png: %.svg
	convert $< $@
