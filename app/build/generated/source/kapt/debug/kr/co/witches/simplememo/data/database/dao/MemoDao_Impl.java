package kr.co.witches.simplememo.data.database.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kr.co.witches.simplememo.model.MemoVO;
import kr.co.witches.simplememo.model.converter.MemoConverters;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MemoDao_Impl implements MemoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MemoVO> __insertionAdapterOfMemoVO;

  private final MemoConverters __memoConverters = new MemoConverters();

  private final EntityDeletionOrUpdateAdapter<MemoVO> __deletionAdapterOfMemoVO;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MemoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMemoVO = new EntityInsertionAdapter<MemoVO>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tb_memo` (`id`,`memo`,`regDate`,`modDate`,`delDate`,`isUse`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemoVO value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        final String _tmp = __memoConverters.memoListToString(value.getMemo());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        stmt.bindLong(3, value.getRegDate());
        stmt.bindLong(4, value.getModDate());
        stmt.bindLong(5, value.getDelDate());
        stmt.bindLong(6, value.isUse());
      }
    };
    this.__deletionAdapterOfMemoVO = new EntityDeletionOrUpdateAdapter<MemoVO>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tb_memo` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MemoVO value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tb_memo;";
        return _query;
      }
    };
  }

  @Override
  public Object insertMemo(final MemoVO stepCount, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMemoVO.insert(stepCount);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteMemo(final MemoVO stepCount, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMemoVO.handle(stepCount);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<MemoVO>> getAll() {
    final String _sql = "SELECT * FROM tb_memo ORDER BY id;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"tb_memo"}, new Callable<List<MemoVO>>() {
      @Override
      public List<MemoVO> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMemo = CursorUtil.getColumnIndexOrThrow(_cursor, "memo");
          final int _cursorIndexOfRegDate = CursorUtil.getColumnIndexOrThrow(_cursor, "regDate");
          final int _cursorIndexOfModDate = CursorUtil.getColumnIndexOrThrow(_cursor, "modDate");
          final int _cursorIndexOfDelDate = CursorUtil.getColumnIndexOrThrow(_cursor, "delDate");
          final int _cursorIndexOfIsUse = CursorUtil.getColumnIndexOrThrow(_cursor, "isUse");
          final List<MemoVO> _result = new ArrayList<MemoVO>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MemoVO _item;
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            final ArrayList<MemoVO> _tmpMemo;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMemo)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMemo);
            }
            _tmpMemo = __memoConverters.stringToMemoList(_tmp);
            final long _tmpRegDate;
            _tmpRegDate = _cursor.getLong(_cursorIndexOfRegDate);
            final long _tmpModDate;
            _tmpModDate = _cursor.getLong(_cursorIndexOfModDate);
            final long _tmpDelDate;
            _tmpDelDate = _cursor.getLong(_cursorIndexOfDelDate);
            final int _tmpIsUse;
            _tmpIsUse = _cursor.getInt(_cursorIndexOfIsUse);
            _item = new MemoVO(_tmpId,_tmpMemo,_tmpRegDate,_tmpModDate,_tmpDelDate,_tmpIsUse);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
