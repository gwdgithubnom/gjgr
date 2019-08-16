// 8queenDoc.cpp : implementation of the CMy8queenDoc class
//

#include "../equeen/8queenDoc.h"

#include "../equeen/8queen.h"
#include "stdafx.h"
#include "8queenDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CMy8queenDoc

IMPLEMENT_DYNCREATE(CMy8queenDoc, CDocument)

BEGIN_MESSAGE_MAP(CMy8queenDoc, CDocument)
	//{{AFX_MSG_MAP(CMy8queenDoc)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CMy8queenDoc construction/destruction

CMy8queenDoc::CMy8queenDoc()
{
	// TODO: add one-time construction code here

}

CMy8queenDoc::~CMy8queenDoc()
{
}

BOOL CMy8queenDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: add reinitialization code here
	// (SDI documents will reuse this document)

	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CMy8queenDoc serialization

void CMy8queenDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: add storing code here
	}
	else
	{
		// TODO: add loading code here
	}
}

/////////////////////////////////////////////////////////////////////////////
// CMy8queenDoc diagnostics

#ifdef _DEBUG
void CMy8queenDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CMy8queenDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CMy8queenDoc commands
