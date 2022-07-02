package com.example.empt

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_button3.*


class SubActivity3 : AppCompatActivity() {
    private val tableName:String = "noteData"
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    var sort: String = "desc"
    lateinit var noteRecycler: RecyclerView
    var layoutStyle: Boolean = true
    var searchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button3)
        databaseCreate() // 데이터베이스 생성 함수
        createTable() // 테이블 생성 함수


        noteRecycler = findViewById(R.id.recyclerView_main)
        // 리사이클러 뷰 설정

        layoutStyle()
        // 리사이클러 뷰 레이아웃 스타일 설정

        val newNotepad:ImageButton = findViewById(R.id.newNoteButton_main)
        // 이미지버튼 설정
        newNotepad.setOnClickListener {
            // 이미지버튼 클릭시
            startActivity(Intent(this, NewNotepadActivity::class.java))
            finish()
            // NewNotepadActivity 클래스를 실행
        }

        if (sort == "desc")
            refreshListDesc()
        else
            refreshListAsc()

        // 이 구간은 작성 및 수정에서 빠져나왔을 때
        // 정렬 값을 기억하고 설정하기 위함임
        backbutton3.setOnClickListener {
            finish()
        }
    }


    private fun layoutStyle() {
        if (layoutStyle) {
            noteRecycler.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            // 스타일이 true일 경우 StaggeredGrid로 설정
            // -> 일반 그리드 레이아웃으로 설정하고자 하면 아래 주석을 해제

            // GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        }
        else {
            noteRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            // 스타일이 false일 경우 리니어레이아웃으로 설정
        }
    }

    fun refreshListAsc() {
        val list = ArrayList<NoteList>()
        // 리스트 설정

        val cursor: Cursor = database.rawQuery(
            "select * from noteData order by time ASC",
            null
        )
        // cursor 설정 (db베이스에서 내림차순으로 불러옴)
        // ASC 생략 가능

        for (i in 0 until cursor.count) {
            // count가 100이면 0 부터 99까지

            cursor.moveToNext() // 커서 이동
            val title = cursor.getString(1)
            val content = cursor.getString(2)
            val color = cursor.getString(4)
            list.add(NoteList(title, content, color))
            // 리스트에 DB에 담긴 내용 추가
        }
        cursor.close()
        // 커서를 닫음

        noteRecycler.adapter = NoteAdapter(list) //리사이클러뷰 어댑터 할당
    }

    fun refreshListDesc() {
        val list = ArrayList<NoteList>()

        val cursor: Cursor = database.rawQuery(
            "select * from noteData order by time DESC",
            null
        )
        // cursor 설정 (db베이스에서 오름차순으로 불러옴)

        for (i in 0 until cursor.count) {
            // count가 100이면 0 부터 99까지
            cursor.moveToNext()
            val title = cursor.getString(1)
            val content = cursor.getString(2)
            val color = cursor.getString(4)
            list.add(NoteList(title, content, color))
        }
        cursor.close()
        noteRecycler.adapter = NoteAdapter(list)
    }

    private fun createTable() {
        database.execSQL(
            "create table if not exists ${tableName}(" +
                    "_id integer PRIMARY KEY autoincrement," +
                    "title text," +
                    "content text," +
                    "time text," +
                    "color text)"
        )
    }

    private fun databaseCreate() {
        // 데이터베이스 생성 | 쓰기 가능한 상태로 설정
        dbHelper = DatabaseHelper(this)
        database = dbHelper.writableDatabase
    }

    // 옵션메뉴 적용
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val search = menu!!.findItem(R.id.menu_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        // 서치뷰 클릭시 힌트 설정

        // 서치뷰의 x버튼 클릭했을때
        searchView.setOnCloseListener {
            searchView.clearFocus()
            // 포커스 제거
            false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    // 텍스트를 입력시 searchQuery 함수 호출
                    searchQuery(newText)
                    searchText = newText

                    /*
                    searchText를 사용하는 이유는
                    검색 후 메모를 삭제시 리스트를 유지하기 위함임
                    */
                }
                return true
            }

        })
        return true
    }

    @SuppressLint("Recycle")
    fun searchQuery(query: String?) {
        val sql = "select * from noteData " +
                "where content Like " + "'%" + query + "%'" + "or title Like " + "'%" + query + "%'" + "order by time DESC"
        /*
        "select * from noteData where content Like '%query%' or title Like '%query%' order by time DESC"
        select 컬럼 from 테이블 | *는 모든 컬럼을 의미함
        where 조건
        content 컬럼내에서 Like(포함하는것) | title 컬럼도 동일함
        입력값이 사과일때
        %query면 썩은사과, 파인사과 등
        query%면 사과가격, 사과하세요 등
        %query%면 황금사과가격, 빨리사과하세요 등
        order by 정렬 | time 컬럼을 기준으로 DESC 내림차순
        order by를 사용하지 않거나 order by time ASC로 하면 오름차순
         */

        val cursor = database.rawQuery(
            "select * from noteData " +
                    "where content Like " + "'%" + query + "%'" + "or title Like " + "'%" + query + "%'" + "order by time DESC",
            null
        )

        val intent = Intent(this@SubActivity3, NoteSearchAdapter::class.java)
        intent.putExtra("sql", sql)
        // 인텐트에 sql문구를 담음

        val recordCount = cursor.count
        // 갯수
        val adapter = NoteSearchAdapter(intent)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_main)

        layoutStyle()

        for (i in 0 until recordCount) {
            cursor.moveToNext()
            val title = cursor.getString(1)
            val content = cursor.getString(2)
            val color = cursor.getString(4)
            adapter.addItem(NoteList(title, content, color))
            // 어댑터에 아이템 추가
        }

        recyclerView.adapter = adapter
        // 리사이클뷰 어댑터 설정

        cursor.close()

    }

    // 옵션메뉴 선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sort -> {
                if (sort == "desc") {
                    Toast.makeText(this, "정렬 (과거순)", Toast.LENGTH_SHORT).show()
                    sort = "asc"
                    refreshListAsc()
                } else {
                    Toast.makeText(this, "정렬 (최신순)", Toast.LENGTH_SHORT).show()
                    sort = "desc"
                    refreshListDesc()
                }
            }

            R.id.menu_layout -> {
                if (layoutStyle) {
                    layoutStyle = false
                    layoutStyle()
                    Toast.makeText(this, "세로 정렬", Toast.LENGTH_SHORT).show()
                } else {
                    layoutStyle = true
                    layoutStyle()
                    Toast.makeText(this, "바둑판 정렬", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}