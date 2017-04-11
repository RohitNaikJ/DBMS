package simpledb.tx.recovery;

import simpledb.buffer.Buffer;
import simpledb.buffer.BufferMgr;
import simpledb.file.Block;
import simpledb.log.BasicLogRecord;
import simpledb.server.SimpleDB;

import java.util.Date;

/**
 * Created by Rohit on 4/10/17.
 */
public class SetTimeRecord implements LogRecord{
    private int txnum, offset;
    private Date val;
    private Block blk;

    public SetTimeRecord(int txnum, Block blk, int offset, Date val) {
        this.txnum = txnum;
        this.offset = offset;
        this.val = val;
        this.blk = blk;
    }

    public SetTimeRecord(BasicLogRecord rec) {
        txnum = rec.nextInt();
        String filename = rec.nextString();
        int blknum = rec.nextInt();
        blk = new Block(filename, blknum);
        offset = rec.nextInt();
        val = rec.nextDate();
    }

    public int writeToLog() {
        Object[] rec = new Object[] {SETDATE, txnum, blk.fileName(),
                blk.number(), offset, val};
        return logMgr.append(rec);
    }

    public int op() {
        return SETDATE;
    }

    public int txNumber() {
        return txnum;
    }

    public String toString() {
        return "<SETDATE " + txnum + " " + blk + " " + offset + " " + val + ">";
    }

    public void undo(int txnum) {
        BufferMgr buffMgr = SimpleDB.bufferMgr();
        Buffer buff = buffMgr.pin(blk);
        buff.setDate(offset, val, txnum, -1);
        buffMgr.unpin(buff);
    }
}
