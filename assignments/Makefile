# One of the generic makefiles.

# +-----------+-----------------------------------------------------------------
# | Variables |
# +-----------+

# Which "group" of files is this?  (Assignments, etc.)
GROUP = $(shell cat document.group.txt)


# +------------------+------------------------------------------------
# | Standard Targets |
# +------------------+

default: html

clean: 
	rm -rf common.xsl *.docbook *.html *.ps *.pdf


# +-----------------------+-------------------------------------------
# | Converting file types |
# +-----------------------+

%.ps: %.html
	html2ps -D -o $*.ps $<

%.pdf: %.ps
	ps2pdf $<  $*.pdf

%.docbook: %.sect
	../tools/mkwrapper $* > $*.docbook

%.html: %.docbook common.xsl
	xsltproc -o $*.html \
		--xinclude \
		--stringparam html.stylesheet "../resources/stylesheet.css" \
		--stringparam process.empty.source.toc "1" \
		--stringparam toc.section.depth "3" \
                --stringparam document.group "$(GROUP)" \
		common.xsl $<


# +---------------+---------------------------------------------------
# | Sets of files |
# +---------------+

sect := $(shell ls *.sect) 	        # Lists all files matching *.sect
docbook := $(shell ls *.docbook)     
sect-html := $(sect:%.sect=%.html)      # Converts .sect to .html
sect-docbook := $(sect:%.sect=%.docbook)

# +---------------------+---------------------------------------------
# | Specialized Targets |
# +---------------------+

html: $(sect-html)                      # Builds all valid .html targets

common.xsl: ../resources/common.xsl
	install $^ $@
