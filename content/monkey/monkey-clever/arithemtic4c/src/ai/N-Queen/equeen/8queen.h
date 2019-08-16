// 8queen.h : main header file for the 8QUEEN application
//

#if !defined(AFX_8QUEEN_H__1C364D00_F998_4F99_81EF_ED39CE607BB1__INCLUDED_)
#define AFX_8QUEEN_H__1C364D00_F998_4F99_81EF_ED39CE607BB1__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "../equeen/resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CMy8queenApp:
// See 8queen.cpp for the implementation of this class
//

class CMy8queenApp : public CWinApp
{
public:
	CMy8queenApp();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CMy8queenApp)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation
	//{{AFX_MSG(CMy8queenApp)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_8QUEEN_H__1C364D00_F998_4F99_81EF_ED39CE607BB1__INCLUDED_)
