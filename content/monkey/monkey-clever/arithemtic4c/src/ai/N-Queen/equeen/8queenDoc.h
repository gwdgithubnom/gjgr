// 8queenDoc.h : interface of the CMy8queenDoc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_8QUEENDOC_H__EAE6024F_09C7_47D9_ADCB_2ABAEA980F17__INCLUDED_)
#define AFX_8QUEENDOC_H__EAE6024F_09C7_47D9_ADCB_2ABAEA980F17__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CMy8queenDoc : public CDocument
{
protected: // create from serialization only
	CMy8queenDoc();
	DECLARE_DYNCREATE(CMy8queenDoc)

// Attributes
public:

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy8queenDoc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CMy8queenDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CMy8queenDoc)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_8QUEENDOC_H__EAE6024F_09C7_47D9_ADCB_2ABAEA980F17__INCLUDED_)
