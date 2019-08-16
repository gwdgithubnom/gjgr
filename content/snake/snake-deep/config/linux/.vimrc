set nocompatible              " be iMproved, required
filetype off                  " required

set rtp+=~/.vim/bundle/Vundle.vim
set encoding=utf-8
set termencoding=utf-8
set fileencoding=utf-8
set fileencodings=ucs-bom,utf-8,chinese,cp936

call vundle#begin()
" let Vundle manage Vundle
" required!

" alternatively, pass a path where Vundle should install plugins
" call vundle#begin('~/some/path/here')
"    vundle#begin(...)

" let Vundle manage Vundle, required
Plugin 'gmarik/Vundle.vim'
" plugin on GitHub repo

"--------------
" IDE features
"--------------
Plugin 'scrooloose/nerdtree'
Plugin 'humiaozuzu/TabBar'
Plugin 'majutsushi/tagbar'
Plugin 'mileszs/ack.vim'
Plugin 'kien/ctrlp.vim'
Plugin 'tpope/vim-fugitive'
Plugin 'Lokaltog/vim-powerline'
Plugin 'scrooloose/syntastic'
Plugin 'bronson/vim-trailing-whitespace'


"------------------
" Code Completions
"------------------
Plugin 'Shougo/neocomplcache'
Plugin 'mattn/emmet-vim'
Plugin 'Raimondi/delimitMate'
Plugin 'ervandew/supertab'
" snippets
Plugin 'garbas/vim-snipmate'
Plugin 'honza/vim-snippets'
"------ snipmate dependencies -------
Plugin 'MarcWeber/vim-addon-mw-utils'
Plugin 'tomtom/tlib_vim'

"-----------------
" Fast navigation
"-----------------
Plugin 'edsono/vim-matchit'
Plugin 'Lokaltog/vim-easymotion'

"--------------
" Fast editing
"--------------
Plugin 'tpope/vim-surround'
Plugin 'scrooloose/nerdcommenter'
Plugin 'sjl/gundo.vim'
Plugin 'godlygeek/tabular'
Plugin 'nathanaelkane/vim-indent-guides'

"-------------
" Other Utils
"-------------
" Bundle 'humiaozuzu/fcitx-status'
Plugin 'nvie/vim-togglemouse'
Bundle 'AutoComplPop' 
Bundle 'FuzzyFinder' 
Plugin 'godlygeek/csapprox'
Plugin 'jlanzarotta/bufexplorer'
Plugin 'ifdef-highlighting'
Bundle 'jsbeautify' 
Bundle 'L9'  
Bundle 'Mark'  

Bundle 'matrix.vim' 
Bundle 'matchit.zip'  

" Bundle 'The-NERD-tree'  
" Bundle 'The-NERD-Commenter' 
Bundle 'drmingdrmer/xptemplate' 
Bundle 'YankRing.vim'

Plugin 'vim-scripts/peaksea'
Plugin 'bling/vim-airline'
Plugin 'vim-airline/vim-airline-themes'
Plugin 'justinmk/vim-syntax-extra'
Plugin 'vim-utils/vim-man'
Plugin 'terryma/vim-multiple-cursors'
Plugin 'terryma/vim-expand-region'
Plugin 'tpope/vim-commentary'
Plugin 'benmills/vimux'
Plugin 'gmarik/vundle'

"----------------------------------------
" Syntax/Indent for language enhancement
"----------------------------------------
"------- web backend ---------
"Plugin '2072/PHP-Indenting-for-VIm'
"Plugin 'tpope/vim-rails'
"Plugin 'lepture/vim-jinja'
"Plugin 'digitaltoad/vim-jade'

"------- web frontend ----------
" Plugin 'othree/html5.vim'
" Plugin 'tpope/vim-haml'
" Plugin 'pangloss/vim-javascript'
" Plugin 'kchmck/vim-coffee-script'
" Plugin 'nono/jquery.vim'
" Plugin 'groenewege/vim-less'
" Plugin 'wavded/vim-stylus'
" Plugin 'nono/vim-handlebars'

"------- markup language -------
Plugin 'tpope/vim-markdown'
Plugin 'timcharper/textile.vim'

"------- Ruby --------
" Plugin 'tpope/vim-endwise'

"------- Go ----------
" Plugin 'fatih/vim-go'

"------- FPs ------
Plugin 'kien/rainbow_parentheses.vim'
" Plugin 'wlangstroth/vim-racket'
" Plugin 'vim-scripts/VimClojure'
" Plugin 'rosstimson/scala-vim-support'

"--------------
" Color Schemes
"--------------
Plugin 'rickharris/vim-blackboard'
Plugin 'altercation/vim-colors-solarized'
Plugin 'rickharris/vim-monokai'
Plugin 'tpope/vim-vividchalk'
Plugin 'Lokaltog/vim-distinguished'
Plugin 'chriskempson/vim-tomorrow-theme'
Plugin 'fisadev/fisa-vim-colorscheme'

call vundle#end()            " required


set guifont=Monaco:h10	"设置字体为Monaco，大小10
" Default Indentation
set autoindent
set smartindent     " indent when 制表符等于几个空格
set tabstop=4       " tab width
set shiftwidth=4        " 制表符缩进时应用几个空格
set expandtab           " 使用扩展制表符
set softtabstop=4   " backspace
set si                  " Smart indent
set shiftwidth=4    " indent width
" set textwidth=79
" set smarttab
set expandtab       " expand tab to space

set wrap                " 文本回滚
set fencs=utf-8,gb18030 " 打卡文件时优先选择编码格式
set ffs=unix,dos,mac    " 文件结束符
set autoread            " 自动读取文件的修改(其他软件的修改)
set nofoldenable        " 关闭折叠
set clipboard=unnamed   " 复制使用系统剪切板

" search 搜索设置
"set highlight 	" conflict with highlight current line
set hlsearch            " 搜索时高亮
set incsearch           " 搜索输入高亮
set nowrapscan          " 关闭搜索回滚
set ignorecase          " 搜索时忽略大小写
set smartcase           " 搜索中含有大写则搜索大写


filetype plugin indent on     " required!
" encoding dectection
set fileencodings=utf-8,gb2312,gb18030,gbk,ucs-bom,cp936,latin1

" enable filetype dectection and ft specific plugin/indent
filetype plugin indent on

" enable syntax hightlight and completion
syntax on
syntax enable "打开语法高亮

set t_Co=256  " Explicitly tell vim that the terminal has 256 colors "
set magic               " 支持正则表达式等
set number              " 显示行号
" set relativenumber      " 显示相对行号

" set scrolloff=10        " 垂直移动时保持n行固定
" set sidescroll=5        " 水平移动时保持n列固定




" editor settings
set history=1000
set nocompatible
set nofoldenable   " disable folding"
set confirm   " prompt when existing from an unsaved file
set backspace=indent,eol,start      " More powerful backspacing
                                                  
set mouse=a    " use mouse in all modes
set report=0     " always report number of lines changed   "
set nowrap       " dont wrap lines
set scrolloff=5         " 5 lines above/below cursor when scrolling
set number        " show line numbers
set showmatch     " show matching bracket (briefly jump)
set showcmd     " show typed command in status bar
set title       " show file in titlebar
set laststatus=2      " use 2 lines for the status bar
set matchtime=2     " show matching bracket for 0.2 seconds
set matchpairs+=<:>     " specially for html



" Quickly edit/reload the vimrc file 重新加载vimrc配置
nmap <silent> <leader>ev :e $MYVIMRC<CR>
nmap <silent> <leader>sv :so $MYVIMRC<CR>
noremap <silent> <leader>V :source $VIM/_vimrc<CR>:filetype detect<CR>:exe ":echo 'vimrc reloaded'"<CR>

" sublime key bindings
nmap <D-]> >>
nmap <D-[> <<
vmap <D-[> <gv
vmap <D-]> >gv

" eggcache vim
nnoremap ; :
:command W w
:command WQ wq
:command Wq wq
:command Q q
:command Qa qa
:command QA qa


"------------------
" Useful Functions
"------------------
" easier navigation between split windows
nnoremap <c-j> <c-w>j
nnoremap <c-k> <c-w>k
nnoremap <c-h> <c-w>h
nnoremap <c-l> <c-w>l

" When editing a file, always jump to the last cursor position
autocmd BufReadPost *
      \ if ! exists("g:leave_my_cursor_position_alone") |
      \     if line("'\"") > 0 && line ("'\"") <= line("$") |
      \         exe "normal g'\"" |
      \     endif |
      \ endif

" w!! to sudo & write a file
cmap w!! %!sudo tee >/dev/null %

" buffere
noremap <silent> <leader>b :ToggleBufExplorer<CR>


" YCM
set completeopt-=preview
let g:ycm_global_ycm_extra_conf = '~/.ycm_extra_conf.py'
nnoremap <leader>jd :YcmCompleter GoToDefinitionElseDeclaration<CR>

" easymotion
map <Leader>l <Plug>(easymotion-lineforward)
map <Leader>h <Plug>(easymotion-linebackward)

" Keybindings for plugin toggle 功能快捷键映射 
set pastetoggle=<F2>
nnoremap <F2> :set invpaste paste?<CR>
nmap <F3> :GundoToggle<cr>
nmap <F4> :IndentGuidesToggle<cr>
nmap <F5> :TagbarToggle<cr>
nmap <F6> :NERDTreeToggle<cr>
nnoremap <silent> <F7> :Grep<CR>   "查找命令
nnoremap <silent> <F9> :YRShow<CR> "打开剪贴板
nnoremap <silent> <F10> :TMiniBufExplorer<CR> "打开/关闭MiniBufExplorer
nnoremap <silent> <F11> :Tlist<CR> "打开/关闭TagList 
nmap  <D-/> :
nnoremap <leader>a :Ack
nnoremap <leader>v V`]

" CtrlP
set wildignore+=*/tmp/*,*.so,*.swp,*.zip     " MacOSX/Linux
set wildignore+=*\\tmp\\*,*.swp,*.zip,*.exe  " Windows
set wildignore+=*/tmp/*,*.so,*.o,*.a,*.obj,*.swp,*.zip,*.pyc,*.pyo,*.class,.DS_Store  " MacOSX/Linux
let g:ctrlp_custom_ignore = {
            \ 'dir':  '\v[\/]\.(git|hg|svn)$',
            \ 'file': '\v\.(exe|so|dll)$',
            \ 'link': 'SOME_BAD_SYMBOLIC_LINKS',
            \ }

let g:ctrlp_custom_ignore = '\.git$\|\.hg$\|\.svn$'

	
	
" ZenCoding
let g:user_emmet_expandabbr_key='<C-j>'

"--------
" Vim UI
"--------
" color scheme
set background=dark
color solarized

" highlight current line
au WinLeave * set nocursorline nocursorcolumn
au WinEnter * set cursorline cursorcolumn
set cursorline cursorcolumn

" Rainbow parentheses for Lisp and variants
let g:rbpt_colorpairs = [
    \ ['brown',       'RoyalBlue3'],
    \ ['Darkblue',    'SeaGreen3'],
    \ ['darkgray',    'DarkOrchid3'],
    \ ['darkgreen',   'firebrick3'],
    \ ['darkcyan',    'RoyalBlue3'],
    \ ['darkred',     'SeaGreen3'],
    \ ['darkmagenta', 'DarkOrchid3'],
    \ ['brown',       'firebrick3'],
    \ ['gray',        'RoyalBlue3'],
    \ ['black',       'SeaGreen3'],
    \ ['darkmagenta', 'DarkOrchid3'],
    \ ['Darkblue',    'firebrick3'],
    \ ['darkgreen',   'RoyalBlue3'],
    \ ['darkcyan',    'SeaGreen3'],
    \ ['darkred',     'DarkOrchid3'],
    \ ['red',         'firebrick3'],
    \ ]
let g:rbpt_max = 16
autocmd Syntax lisp,scheme,clojure,racket RainbowParenthesesToggle


" powerline
"let g:Powerline_symbols = 'fancy'


	
" vim-airline 设置VIM头，脚
let g:airline#extensions#tabline#enabled = 1
let g:airline#extensions#tabline#left_sep = ' '
let g:airline#extensions#tabline#left_alt_sep = '|'



"===========================
"状态栏的设置
"===========================
set statusline=[%F]%y%r%m%*%=[Line:%l/%L,Column:%c][%p%%] "显示文件名：总行数，总的字符数
set ruler "在编辑过程中，在右下角显示光标位置的状态行
 

" set list                " show trailing whitespace
" set listchars=tab:▸\ ,trail:▫

" 补全命令设置
set wildmenu
set wildignore+=*.o,*.obj,*.pyc,*.aux,*.bbl,*.blg,.git,.svn,.hg

autocmd FileType php setlocal tabstop=2 shiftwidth=2 softtabstop=2 textwidth=120
autocmd FileType ruby setlocal tabstop=2 shiftwidth=2 softtabstop=2 textwidth=120
autocmd FileType php setlocal tabstop=4 shiftwidth=4 softtabstop=4 textwidth=120
autocmd FileType coffee,javascript setlocal tabstop=2 shiftwidth=2 softtabstop=2 textwidth=120
autocmd FileType python setlocal tabstop=4 shiftwidth=4 softtabstop=4 textwidth=120
autocmd FileType html,htmldjango,xhtml,haml setlocal tabstop=2 shiftwidth=2 softtabstop=2 textwidth=0
autocmd FileType sass,scss,css setlocal tabstop=2 shiftwidth=2 softtabstop=2 textwidth=120

"-----------------
" Plugin settings
"-----------------
" syntax support
autocmd Syntax javascript set syntax=jquery   " JQuery syntax support
" js
let g:html_indent_inctags = "html,body,head,tbody"
let g:html_indent_script1 = "inc"
let g:html_indent_style1 = "inc"

" NeoComplCache
let g:neocomplcache_enable_at_startup=1
let g:neoComplcache_disableautocomplete=1
"let g:neocomplcache_enable_underbar_completion = 1
"let g:neocomplcache_enable_camel_case_completion = 1
let g:neocomplcache_enable_smart_case=1
let g:neocomplcache_min_syntax_length = 3
let g:neocomplcache_lock_buffer_name_pattern = '\*ku\*'
set completeopt-=preview

imap <C-k> <Plug>(neocomplcache_snippets_force_expand)
smap <C-k> <Plug>(neocomplcache_snippets_force_expand)
imap <C-l> <Plug>(neocomplcache_snippets_force_jump)
smap <C-l> <Plug>(neocomplcache_snippets_force_jump)

" Enable omni completion.
autocmd FileType css setlocal omnifunc=csscomplete#CompleteCSS
autocmd FileType html,markdown setlocal omnifunc=htmlcomplete#CompleteTags
autocmd FileType javascript setlocal omnifunc=javascriptcomplete#CompleteJS
autocmd FileType python setlocal omnifunc=pythoncomplete#Complete
autocmd FileType c setlocal omnifunc=ccomplete#Complete
if !exists('g:neocomplcache_omni_patterns')
  let g:neocomplcache_omni_patterns = {}
endif
let g:neocomplcache_omni_patterns.erlang = '[a-zA-Z]\|:'

" Tagbar
let g:tagbar_left=1
let g:tagbar_width=30
let g:tagbar_autofocus = 1
let g:tagbar_sort = 0
let g:tagbar_compact = 1
nmap <F9> :TagbarToggle<CR>
let g:tagbar_autoclose = 1
" nnoremap <silent> <F8> :TagbarToggle<CR>
nnoremap <silent> <leader>t :TagbarToggle<CR>

" SuperTab
" let g:SuperTabDefultCompletionType='context'
let g:SuperTabDefaultCompletionType = '<C-X><C-U>'
let g:SuperTabRetainCompletionType=2

" tabbar
let g:Tb_MaxSize = 2
let g:Tb_TabWrap = 1

hi Tb_Normal guifg=white ctermfg=white
hi Tb_Changed guifg=green ctermfg=green
hi Tb_VisibleNormal ctermbg=252 ctermfg=235
hi Tb_VisibleChanged guifg=green ctermbg=252 ctermfg=white

" Nerd Tree
let NERDChristmasTree=0
let NERDTreeWinSize=30
let NERDTreeChDirMode=2
let g:neocomplcache_enable_at_startup = 1 
" let g:NERDTreeDirArrowExpandable = '->'
" let g:NERDTreeDirArrowCollapsible = '▾'
" noremap <silent> <leader>d :NERDTreeToggle<CR>
let NERDTreeIgnore=['\~$', '\.pyc$', '\.swp$']
" let NERDTreeSortOrder=['^__\.py$', '\/$', '*', '\.swp$',  '\~$']
let NERDTreeShowBookmarks=1
let NERDTreeWinPos = "left"
" 是否显示隐藏文件
let NERDTreeShowHidden=1
" 关闭NERDTree快捷键
" map <leader>t :NERDTreeToggle<CR>
map <C-n> :NERDTreeToggle<CR>

" nerdcommenter
let NERDSpaceDelims=1
" nmap <D-/> :NERDComToggleComment<cr>
let NERDCompactSexyComs=1