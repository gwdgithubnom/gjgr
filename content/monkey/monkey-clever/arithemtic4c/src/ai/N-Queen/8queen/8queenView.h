// 8queenView.h : interface of the CMy8queenView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_8QUEENVIEW_H__381860DD_9B08_438F_836E_91C3D995EC79__INCLUDED_)
#define AFX_8QUEENVIEW_H__381860DD_9B08_438F_836E_91C3D995EC79__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CMy8queenView : public CView
{
protected: // create from serialization only
	CMy8queenView();
	DECLARE_DYNCREATE(CMy8queenView)

// Attributes
public:
	CMy8queenDoc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy8queenView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);
	//}}AFX_VIRTUAL

// Implementation
public:
	CBrush m_brush2;
	CBrush m_brush1;
	CPen m_pen;
	virtual ~CMy8queenView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CMy8queenView)
	afx_msg void OnShow();
	afx_msg void OnDelete();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in 8queenView.cpp
inline CMy8queenDoc* CMy8queenView::GetDocument()
   { return (CMy8queenDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_8QUEENVIEW_H__381860DD_9B08_438F_836E_91C3D995EC79__INCLUDED_)
