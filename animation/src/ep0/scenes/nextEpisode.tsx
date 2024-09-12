import {makeScene2D, Txt} from '@motion-canvas/2d';
import {createRef, DEFAULT, Direction, slideTransition, waitUntil} from '@motion-canvas/core';
import {Caption} from '../../common';

export default makeScene2D(function* (view) {
  const title = createRef<Txt>();
  const next = createRef<Txt>();

  view.add(
    <>
      <Caption ref={title} text={'Next episode'} textAlign={'center'}/>
      {/*<Caption ref={next} text={'Coroutine fundamentals'} opacity={0}/>*/}
    </>
  );

  yield* slideTransition(Direction.Right);

  yield* waitUntil('start');

  title().size(title().size());
  yield* title().text('', 1);
  title().size(DEFAULT);
  yield* title().text('Coroutine fundamentals', 1);



  yield* waitUntil('end');
});
