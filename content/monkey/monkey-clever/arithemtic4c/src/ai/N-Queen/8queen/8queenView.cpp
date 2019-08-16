// 8queenView.cpp : implementation of the CMy8queenView class
//

#include "stdafx.h"
#include "8queen.h"

#include "8queenDoc.h"
#include "8queenView.h"

#include<iostream.h>
#include<stdlib.h>
#include<math.h>
#include<time.h>
#define POPSIZE 50
#define MAXGEN 100000
#define CHROMLEN 14
#define PC   0.7
#define PM   0.1
typedef struct
{
	unsigned *chrom;
	double fitness;
}individual;
individual *oldpop,*newpop,bestever;

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif


void initiate()
{
	int i;
	oldpop=(individual *)malloc(POPSIZE*sizeof(individual));
	newpop=(individual *)malloc(POPSIZE*sizeof(individual));
	for(i=0;i<POPSIZE;i++)
	{
		oldpop[i].chrom=(unsigned *)malloc(CHROMLEN*sizeof(unsigned));
		newpop[i].chrom=(unsigned *)malloc(CHROMLEN*sizeof(unsigned));
	}
	bestever.chrom=(unsigned *)malloc(CHROMLEN*sizeof(unsigned));
	bestever.fitness=0.0;
	//bestever.rfitness=0.0;
	//bestever.cfitness=0.0;
}
void coding()
{
	int i,j;
	initiate();
	srand((unsigned)time(0));
	for(i=0;i<POPSIZE;i++)
	{
		for(j=0;j<CHROMLEN;j++)
		{
			oldpop[i].chrom[j]=(int)rand()%8;
			//cout<<oldpop[i].chrom[j];
		}
		//cout<<"   "<<i<<endl;
	}
}

double fitness_count(individual temp)
{
	int i,j,k,fit;
	double sum=0.0;
	//for(i=0;i<POPSIZE;i++)
	//{
		fit=0;
		for(j=0;j<CHROMLEN-1;j++)
			for(k=j+1;k<CHROMLEN;k++)
			{
				if(temp.chrom[j]==temp.chrom[k])
					fit++;
			}
		for(j=0;j<CHROMLEN;j++)
		{
			unsigned t=temp.chrom[j];
			int m=j;
			while(t>0&&m<CHROMLEN-1)
			{
				t--;
				m++;
				if(t==temp.chrom[m])
					fit++;
			}
			t=temp.chrom[j];
			m=j;
			while((++t)<CHROMLEN&&(++m)<CHROMLEN)
			{
				if(t==temp.chrom[m])
					fit++;
			}
		}
		sum=(double)1.0/(fit+1);

		return sum;
}

double sumfitness(individual *temp)
{
	int i;
	double sum=0.0;
	for(i=0;i<CHROMLEN;i++)
		sum+=temp[i].fitness;
	return sum;
}


void encode()
{
	int i;
	for(i=0;i<POPSIZE;i++)
	{
		oldpop[i].fitness=fitness_count(oldpop[i]);
		//cout<<oldpop[i].fitness<<endl;
	}
}
int select()
{
	int i;
	double sumfit,frand,fsum=0.0;
	sumfit=sumfitness(oldpop);
	frand=(double)(rand()%100)/100;
	for(i=0;i<CHROMLEN;i++)
	{
		fsum+=oldpop[i].fitness/sumfit;
		if(fsum>frand)
			break;
	}
	return i;
}
void crossover(individual parent1,individual parent2,individual child1,individual child2)
{
	int i,j,spot;
	if((double)(rand()%100)/100<PC)
	{
		spot=rand()%CHROMLEN;
		//cout<<spot<<endl;
		for(j=0;j<spot;j++)
		{
			child1.chrom[j]=parent1.chrom[j];
			child2.chrom[j]=parent2.chrom[j];
		}
		for(j=spot;j<CHROMLEN;j++)
		{
			child1.chrom[j]=parent2.chrom[j];
			child2.chrom[j]=parent1.chrom[j];
		}
	}
	else
	{
		for(j=0;j<CHROMLEN;j++)
		{
			child1.chrom[j]=parent1.chrom[j];
			child2.chrom[j]=parent2.chrom[j];
		}
	}

}
void mutation(individual temp)
{
	int spot;
	if((double)(rand()%100)/100<PM)
	{
		spot=rand()%CHROMLEN;
		temp.chrom[spot]=rand()%CHROMLEN;
	}
}
void evolution()
{
	int i=0,j,mate1,mate2;
	while(i<POPSIZE)
	{
		mate1=select();
		mate2=select();
		//cout<<mate1<<"  "<<mate2<<endl;
		crossover(oldpop[mate1],oldpop[mate2],newpop[i],newpop[i+1]);
		mutation(newpop[i]);
		newpop[i].fitness=fitness_count(newpop[i]);
		mutation(newpop[i+1]);
		newpop[i+1].fitness=fitness_count(newpop[i+1]);
		i+=2;
	}

}
void output()
{
	int i,j;
	for(i=0;i<POPSIZE;i++)
	{
		if(bestever.fitness<newpop[i].fitness)
		{
			for(j=0;j<CHROMLEN;j++)
			{
				bestever.chrom[j]=newpop[i].chrom[j];
			}
			bestever.fitness=newpop[i].fitness;
		}
	}
	//cout<<"最优解:  ";
	/*for(j=0;j<CHROMLEN;j++)
		cout<<bestever.chrom[j];
	cout<<endl;*/
}

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView

IMPLEMENT_DYNCREATE(CMy8queenView, CView)

BEGIN_MESSAGE_MAP(CMy8queenView, CView)
	//{{AFX_MSG_MAP(CMy8queenView)
	ON_COMMAND(ID_SHOW, OnShow)
	ON_COMMAND(ID_DELETE, OnDelete)
	//}}AFX_MSG_MAP
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, CView::OnFilePrintPreview)
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView construction/destruction

CMy8queenView::CMy8queenView()
{
	// TODO: add construction code here
	m_pen.CreatePen(PS_SOLID,1,RGB(0,0,0));
	m_brush1.CreateSolidBrush(RGB(0,0,0));
	m_brush2.CreateSolidBrush(RGB(255,255,0));

}

CMy8queenView::~CMy8queenView()
{
}

BOOL CMy8queenView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView drawing

void CMy8queenView::OnDraw(CDC* pDC)
{
	int i,j;
	CMy8queenDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	CClientDC dc(this);
	dc.SelectObject(m_pen);
	dc.Rectangle(100,50,550,400);
	for(i=0;i<CHROMLEN;i++)
	{
		if(i%2==0)
		{
			for(j=0;j<CHROMLEN;j++)
			{
			
				if(j%2==0)
				{
					dc.SelectObject(m_brush1);
					dc.Rectangle(100+i*450/CHROMLEN,50+j*350/CHROMLEN,100+(i+1)*450/CHROMLEN,50+(j+1)*350/CHROMLEN);
				}
			}
		}
		else
		{
			for(j=0;j<CHROMLEN;j++)
			{
				if(j%2==1)
				{
					dc.SelectObject(m_brush1);
					dc.Rectangle(100+i*450/CHROMLEN,50+j*350/CHROMLEN,100+(i+1)*450/CHROMLEN,50+(j+1)*350/CHROMLEN);
				}
			}
			//else if(i%2==1)
		}
	}
		

	// TODO: add draw code for native data here
}

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView printing

BOOL CMy8queenView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CMy8queenView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add extra initialization before printing
}

void CMy8queenView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add cleanup after printing
}

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView diagnostics

#ifdef _DEBUG
void CMy8queenView::AssertValid() const
{
	CView::AssertValid();
}

void CMy8queenView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CMy8queenDoc* CMy8queenView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CMy8queenDoc)));
	return (CMy8queenDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CMy8queenView message handlers

void CMy8queenView::OnShow() 
{
	// TODO: Add your command handler code here
	CClientDC dc(this);
	CPaintDC dc2(this);
    OnDraw(&dc2); //调用了OnDraw
	//CWnd *pWnd;
	//pWnd->GetDC();
	dc.SelectObject(m_brush2);
	int i,j,count=0;
	CString str;
	individual *tempop;
	coding();
	encode();
	for(i=1;i<=MAXGEN&&bestever.fitness!=1.0;i++)
	{
		evolution();
		output();
		tempop=oldpop;
		oldpop=newpop;
		newpop=tempop;
		count++;
	}
	for(i=0;i<CHROMLEN;i++)
	{
			
		dc.SelectObject(m_brush2);
		dc.Ellipse(100+i*450/CHROMLEN,50+bestever.chrom[i]*350/CHROMLEN,100+(i+1)*450/CHROMLEN,50+(bestever.chrom[i]+1)*350/CHROMLEN);
	}
	str.Format("最优解出现代数%d",count);
	dc.TextOut(580,200,str);
	
	

	
}

void CMy8queenView::OnDelete() 
{
    CPaintDC dc(this);
    OnDraw(&dc); //调用了OnDraw


	// TODO: Add your command handler code here
	
}
