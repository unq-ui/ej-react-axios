import React from'react';

function StickyNote({ note, removeNote, editNote }) {
  const { id, text, color } = note;
  return (
    <div className={`stickyNote stickyNote--${color}`}>
      <div className="actions">
        <img src="./img/edit.svg" alt="edit" className="icon" onClick={editNote}/>
        <img src="./img/delete.svg" alt="edit" className="icon" onClick={() => removeNote(id)}/>
      </div>
      <p className="text">{text}</p>
    </div>
  );
}

export default StickyNote;