import React, { useState } from 'react';

const Modal = (props) => {
  const { selectedNote, addNote, closeModal } = props;
  const [color, setColor] = useState(selectedNote ? selectedNote.color : 'yellow');
  const [text, setText] = useState(selectedNote ? selectedNote.text : '');
  const id = selectedNote ? selectedNote.id : undefined;
  
  const colors = ['orange', 'blue', 'yellow', 'pink', 'green'];
  
  const updateText = ev => setText(ev.target.value);

  const addNoteOnClick = () => {
    addNote({ color, text, id });
    closeModal();
  }

  return (
    <div className="background">
      <div className="modal">
        <div className="modal__header">
          <h2 className="header">Sticky Note</h2>
          <div className="colors">
            {colors.map(c => (
              <div key={c} className={`color color--${c}`} onClick={() => setColor(c)}>
                <img className={`check ${c === color && 'check--showIcon'}`} src="img/check.svg" alt="check" />
              </div>
            ))}
          </div>
        </div>
        <div className="modal__content">
          <div className={`textarea__container color--${color}`}>
            <textarea type="text" value={text} onChange={updateText} maxLength="248" rows="7" />
          </div>
          <div className="btns">
            <div className="btn-modal" type="submit" onClick={closeModal}>Cancel</div>
            <div className="btn-modal" type="submit" onClick={addNoteOnClick}>Save</div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Modal;